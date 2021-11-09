package com.fiuni.sd.service.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fiuni.sd.dao.IServicioDao;
import com.fiuni.sd.domain.servicio.ServicioDomain;
import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.fiuni.sd.service.base.BaseServiceImpl;

@Service
public class ServicioServiceImpl extends BaseServiceImpl<ServicioDTO, ServicioDomain, ServicioResult> implements IServicioService{

	@Autowired
	private IServicioDao servicioDao;
	
	@Override
	public ServicioDTO save(ServicioDTO dto) {
		final ServicioDomain servicioDomain = convertDtoToDomain(dto);
		final ServicioDomain servicio = servicioDao.save(servicioDomain);
		return convertDomainToDto(servicio);
	}

	@Override
	public ServicioDTO getById(Integer id) {
		Optional<ServicioDomain> result = servicioDao.findById(id);
        ServicioDTO servicio = null;
        if(result.isPresent()){
        	servicio = convertDomainToDto(result.get());
        } else {
            throw new RuntimeException("Did not find product id: " + id);
        }
        return servicio;
	}

	@Override
	public ServicioResult getAll(Pageable pageable) {
		final List<ServicioDTO> servicios = new ArrayList<>();
		Page<ServicioDomain> results = servicioDao.findAll(pageable);
		for (ServicioDomain servicioDomain : results) {
			servicios.add(convertDomainToDto(servicioDomain));
		}
		ServicioResult servicioResult = new ServicioResult();
		servicioResult.setServicios(servicios);
		return servicioResult;
	}

	@Override
	public void deleteById(int id) {
		servicioDao.deleteById(id);
	}

	@Override
	protected ServicioDTO convertDomainToDto(ServicioDomain domain) {
		final ServicioDTO dto = new ServicioDTO();
		dto.setId(domain.getId());
		dto.setDescripcion(domain.getDescripcion());
		dto.setCosto(domain.getCosto());
        return dto;
	}

	@Override
	protected ServicioDomain convertDtoToDomain(ServicioDTO dto) {
		final ServicioDomain domain = new ServicioDomain();
		domain.setId(dto.getId());
		domain.setDescripcion(dto.getDescripcion());
		domain.setCosto(dto.getCosto());
        return domain;
	}
}