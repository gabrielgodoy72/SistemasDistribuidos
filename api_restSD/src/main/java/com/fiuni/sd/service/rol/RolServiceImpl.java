package com.fiuni.sd.service.rol;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IRolDao;
import com.fiuni.sd.domain.credenciales.RoleDomain;
import com.fiuni.sd.dto.rol.RolDTO;
import com.fiuni.sd.dto.rol.RolResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
public class RolServiceImpl extends BaseServiceImpl<RolDTO, RoleDomain, RolResult> implements IRolService {

	@Autowired
	private IRolDao rolDao; // repository

	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_rol_' + #result.getId()")
	public RolDTO save(final RolDTO dto) {
		return convertDomainToDto(rolDao.save(convertDtoToDomain(dto)));
	}
	
	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_rol_' + count")
	public Integer count() {
		Integer countCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_rol_count", Integer.class);
		if (countCacheado != null) {
			return countCacheado;
		}
		return (int) rolDao.count();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_rol_' + #id")
	public RolDTO getById(final Integer id) {
		RolDTO rolCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_rol_" + id, RolDTO.class);
		if (rolCacheado != null) {
			return rolCacheado;
		}
		return rolDao.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_rol_' + #nombre")
	public RolDTO getByNombre(final String nombre) {
		RolDTO rolCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_rol_" + nombre, RolDTO.class);
		if (rolCacheado != null) {
			return rolCacheado;
		}
		return rolDao.findByNombre(nombre)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_rol_' + #descripcion")
	public RolDTO getByDescripcion(final String descripcion) {
		RolDTO rolCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_rol_" + descripcion, RolDTO.class);
		if (rolCacheado != null) {
			return rolCacheado;
		}
		return rolDao.findByDescripcion(descripcion)//
				.map(this::convertDomainToDto)//
				.orElseThrow();
	}

	@Override
	public RolResult getAll(final Pageable pageable) {
		final List<RolDTO> list = new ArrayList<>();
		final RolResult result = new RolResult();
		Page<RoleDomain> pages = rolDao.findAll(pageable);
		pages.forEach(rol -> {
			RolDTO dto = convertDomainToDto(rol);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_rol_" + dto.getId(), dto);
		});
		result.setRoles(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		result.setTotal((int) rolDao.count());
		result.set_hasPrev(pages.hasPrevious());
		result.set_hasNext(pages.hasNext());
		return result;
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_rol_' + #id")
	public RolDTO deleteById(final Integer id) {
		if (!rolDao.existsById(id)) {
			throw new ResourceNotFoundException("Rol", "id", id);
		}
		RolDTO rol = convertDomainToDto(rolDao.getById(id));
		rolDao.deleteById(id);
		return rol;
	}

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_rol_' + #id")
	public RolDTO update(final Integer id, final RolDTO dto) {
		if (!rolDao.existsById(id)) {
			throw new ResourceNotFoundException("Rol", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Rol", "id", id);
		}
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_rol_" + id);
		return convertDomainToDto(rolDao.save(convertDtoToDomain(dto)));
	}

	@Override
	protected RolDTO convertDomainToDto(final RoleDomain domain) {
		final RolDTO dto = new RolDTO();
		dto.setId(domain.getId());
		dto.setNombre(domain.getNombre());
		dto.setDescripcion(domain.getDescripcion());
		return dto;
	}

	@Override
	protected RoleDomain convertDtoToDomain(final RolDTO dto) {
		final RoleDomain domain = new RoleDomain();
		domain.setId(dto.getId());
		domain.setNombre(dto.getNombre());
		domain.setDescripcion(dto.getDescripcion());
		return domain;
	}

}
