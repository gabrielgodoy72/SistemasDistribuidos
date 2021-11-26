package com.fiuni.sd.service.servicio;

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
import org.springframework.transaction.annotation.Transactional;

import com.fiuni.sd.dao.IServicioDao;
import com.fiuni.sd.domain.servicio.ServicioDomain;
import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;
import com.fiuni.sd.utils.Setting;

@Service
@Transactional
public class ServicioServiceImpl extends BaseServiceImpl<ServicioDTO, ServicioDomain, ServicioResult>
		implements IServicioService {

	@Autowired
	private IServicioDao repository; // repository
	
	@Autowired
	private CacheManager cacheManager;

	@Override
	@CachePut(value = Setting.CACHE_NAME, key = "'api_servicio_' + #result.getId()")
	public ServicioDTO save(final ServicioDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_servicio_' + #id")
	public ServicioDTO getById(final Integer id) {
		ServicioDTO servicioCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_servicio_" + id, ServicioDTO.class);
		if (servicioCacheado != null) {
			return servicioCacheado;
		}
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Servicio", "id", id));
	}

	@Override
	@Cacheable(value = Setting.CACHE_NAME, key = "'api_servicio_' + #result.getDescripcion()")
	public ServicioDTO getByDescripcion(String descripcion) {
		ServicioDTO servicioCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
				.get("api_servicio_" + descripcion, ServicioDTO.class);
		if (servicioCacheado != null) {
			return servicioCacheado;
		}
		return repository.findByDescripcion(descripcion)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Servicio", "descripcion", descripcion));
	}

	@Override
	public ServicioResult getAll(final Pageable pageable) {
		final List<ServicioDTO> list = new ArrayList<>();
		final ServicioResult result = new ServicioResult();
		Page<ServicioDomain> pages = repository.findAll(pageable);
		pages.forEach(servicio -> {
			ServicioDTO dto = convertDomainToDto(servicio);
			list.add(dto);
			cacheManager.getCache(Setting.CACHE_NAME).put("api_servicio_" + dto.getId(), dto);
		});
		result.setServicios(list);
		result.setPage(pages.getNumber());
		result.setTotalPages(pages.getTotalPages());
		return result;
	}

	@Override
	public void deleteById(final Integer id) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Servicio", "id", id);
		}
		repository.deleteById(id);
		cacheManager.getCache(Setting.CACHE_NAME).evict("api_servicio_" + id);
	}

	@Override
	@CacheEvict(value = Setting.CACHE_NAME, key = "'api_servicio_' + #id")
	@CachePut(value = Setting.CACHE_NAME, key = "'api_servicio_' + #id")
	public ServicioDTO update(final Integer id, final ServicioDTO dto) {
		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Servicio", "id", id);
		}
		if (id != dto.getId()) {
			throw new ResourceNotFoundException("Servicio", "id", id);
		}
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	protected ServicioDTO convertDomainToDto(final ServicioDomain domain) {
		final ServicioDTO dto = new ServicioDTO();
		dto.setId(domain.getId());
		dto.setDescripcion(domain.getDescripcion());
		dto.setCosto(domain.getCosto());
		return dto;
	}

	@Override
	protected ServicioDomain convertDtoToDomain(final ServicioDTO dto) {
		final ServicioDomain domain = new ServicioDomain();
		domain.setId(dto.getId());
		domain.setDescripcion(dto.getDescripcion());
		domain.setCosto(dto.getCosto());
		return domain;
	}

}