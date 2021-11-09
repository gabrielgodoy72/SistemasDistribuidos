package com.fiuni.sd.service.proveedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IProveedorDao;
import com.fiuni.sd.domain.proveedor.ProveedorDomain;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.fiuni.sd.service.base.BaseServiceImpl;

@Service
public class ProveedorServiceImpl extends BaseServiceImpl<ProveedorDTO, ProveedorDomain, ProveedorResult> implements IProveedorService{

	@Autowired
	private IProveedorDao proveedorDao;
	
	@Override
	public ProveedorDTO save(ProveedorDTO dto) {
		final ProveedorDomain proveedorDomain = convertDtoToDomain(dto);
		final ProveedorDomain proveedor = proveedorDao.save(proveedorDomain);
		return convertDomainToDto(proveedor);
	}

	@Override
	public ProveedorDTO getById(Integer id) {
		Optional<ProveedorDomain> result = proveedorDao.findById(id);
        ProveedorDTO proveedor = null;
        if(result.isPresent()){
        	proveedor = convertDomainToDto(result.get());
        } else {
            throw new RuntimeException("Did not find proveedor id: " + id);
        }
        return proveedor;
	}

	@Override
	public ProveedorResult getAll(Pageable pageable) {
		final List<ProveedorDTO> proveedores = new ArrayList<>();
		Page<ProveedorDomain> results = proveedorDao.findAll(pageable);
		for (ProveedorDomain proveedorDomain : results) {
			proveedores.add(convertDomainToDto(proveedorDomain));
		}
		ProveedorResult proveedorResult = new ProveedorResult();
		proveedorResult.setProveedores(proveedores);
		return proveedorResult;
	}

	@Override
	public void deleteById(int id) {
		proveedorDao.deleteById(id);
	}

	@Override
	protected ProveedorDTO convertDomainToDto(ProveedorDomain domain) {
		final ProveedorDTO dto = new ProveedorDTO();
		dto.setId(domain.getId());
		dto.setNombre(domain.getNombre());
		dto.setRuc(domain.getRuc());
		dto.setDireccion(domain.getDireccion());
		dto.setTelefono(domain.getTelefono());
        return dto;
	}

	@Override
	protected ProveedorDomain convertDtoToDomain(ProveedorDTO dto) {
		final ProveedorDomain domain = new ProveedorDomain();
		domain.setId(dto.getId());
		domain.setNombre(dto.getNombre());
		domain.setRuc(dto.getRuc());
		domain.setDireccion(dto.getDireccion());
		domain.setTelefono(dto.getTelefono());
        return domain;
	}



}
