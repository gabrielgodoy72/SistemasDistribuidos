package com.fiuni.sd.service.proveedor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.dao.IProveedorDao;
import com.fiuni.sd.domain.proveedor.ProveedorDomain;
import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
@Transactional
public class ProveedorServiceImpl extends BaseServiceImpl<ProveedorDTO, ProveedorDomain, ProveedorResult>
		implements IProveedorService {

	@Autowired
	private IProveedorDao proveedorDao; // repository
	
	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_proveedor_' + #result.getId()" )
	public ProveedorDTO save(ProveedorDTO dto) {
		return convertDomainToDto(proveedorDao.save(convertDtoToDomain(dto)));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_proveedor_' + #id")
	public ProveedorDTO getById(Integer id) {
		return proveedorDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "id", id));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_proveedor_' + #nombre")
	public ProveedorDTO getByNombre(String nombre) {
		return proveedorDao.findByNombre(nombre)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "nombre", nombre));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_proveedor_' + #ruc")
	public ProveedorDTO getByRuc(String ruc) {
		return proveedorDao.findByRuc(ruc)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Proveedor", "ruc", ruc));
	}

	@Override
	public ProveedorResult getAll(Pageable pageable) {
		final List<ProveedorDTO> list = new ArrayList<>();
		final ProveedorResult result = new ProveedorResult();
		Page<ProveedorDomain> pages = proveedorDao.findAll(pageable);
		pages.forEach(proveedor -> {
			ProveedorDTO dto = convertDomainToDto(proveedor);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_proveedor_" + dto.getId(), dto);
		});
		result.setProveedores(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
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
