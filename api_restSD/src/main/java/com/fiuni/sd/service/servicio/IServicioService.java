package com.fiuni.sd.service.servicio;

import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IServicioService extends IBaseService<ServicioDTO, ServicioResult> {
	
	public ServicioDTO update(final Integer id, final ServicioDTO dto);

	public ServicioDTO getByDescripcion(final String descripcion);

}