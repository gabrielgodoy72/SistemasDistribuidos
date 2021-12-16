package com.sd.service.servicio;

import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.sd.beans.servicio.ServicioB;
import com.sd.service.base.IBaseService;

public interface IServicioService extends IBaseService<ServicioB, ServicioDTO> {

    public ServicioB getByDescripcion(String descripcion);

}
