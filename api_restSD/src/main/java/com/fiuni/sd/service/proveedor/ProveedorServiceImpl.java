package com.fiuni.sd.service.proveedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IProveedorDao;
import com.fiuni.sd.domain.proveedor.ProveedorDomain;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class ProveedorServiceImpl extends BaseServiceImpl<ProveedorDTO, ProveedorDomain, ProveedorResult>
		implements IProveedorService {

	@Autowired
	private IProveedorDao proveedorDao; // repository

	@Override
	public ProveedorDTO save(ProveedorDTO dto) {
		return convertDomainToDto(proveedorDao.save(convertDtoToDomain(dto)));
	}

	@Override
	public ProveedorDTO getById(Integer id) {
		return proveedorDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "id", id));
	}

	@Override
	public ProveedorDTO getByNombre(String nombre) {
		return proveedorDao.findByNombre(nombre)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "nombre", nombre));
	}

	@Override
	public ProveedorDTO getByRuc(String ruc) {
		return proveedorDao.findByRuc(ruc)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "ruc", ruc));
	}

	@Override
	public ProveedorResult getAll(Pageable pageable) {
		final ProveedorResult result = new ProveedorResult();
		result.setProveedores(proveedorDao.findAll(pageable)//
				.map(this::convertDomainToDto)//
				.toList());
		return result;
	}

	@Override
	public void deleteById(Integer id) {
		if (!proveedorDao.existsById(id)) {
			throw new ResourceNotFoundException("Proveedor", "id", id);
		}
		proveedorDao.deleteById(id);
	}

	@Override
	public ProveedorDTO update(Integer id, ProveedorDTO dto) {
		if (!proveedorDao.existsById(id)) {
			throw new ResourceNotFoundException("Proveedor", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Proveedor", "id", id);
		}
		return convertDomainToDto(proveedorDao.save(convertDtoToDomain(dto)));
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
