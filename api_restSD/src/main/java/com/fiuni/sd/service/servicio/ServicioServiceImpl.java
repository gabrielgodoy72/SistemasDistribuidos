package com.fiuni.sd.service.servicio;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IServicioDao;
import com.fiuni.sd.domain.servicio.ServicioDomain;
import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.fiuni.sd.service.base.BaseServiceImpl;
import com.fiuni.sd.utils.ResourceNotFoundException;

@Service
public class ServicioServiceImpl extends BaseServiceImpl<ServicioDTO, ServicioDomain, ServicioResult>
		implements IServicioService {

	@Autowired
	private IServicioDao repository; // repository

	@Override
	public ServicioDTO save(final ServicioDTO dto) {
		return convertDomainToDto(repository.save(convertDtoToDomain(dto)));
	}

	@Override
	public ServicioDTO getById(final Integer id) {
		return repository.findById(id)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Servicio", "id", id));
	}

	@Override
	public ServicioDTO getByDescripcion(String descripcion) {
		return repository.findByDescripcion(descripcion)//
				.map(this::convertDomainToDto)//
				.orElseThrow(() -> new ResourceNotFoundException("Servicio", "descripcion", descripcion));
	}

	@Override
	public ServicioResult getAll(final Pageable pageable) {
		final ServicioResult result = new ServicioResult();
		Page<ServicioDomain> pages = repository.findAll(pageable);
		result.setServicios(pages.getContent()//
				.stream()//
				.map(this::convertDomainToDto)//
				.collect(Collectors.toList()));
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
	}

	@Override
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