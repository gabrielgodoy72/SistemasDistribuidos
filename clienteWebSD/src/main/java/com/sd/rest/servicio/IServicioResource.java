package com.sd.rest.servicio;

import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.sd.rest.base.IBaseResource;

public interface IServicioResource extends IBaseResource<ServicioDTO> {

    public ServicioResult getAll(Integer page);

    public ServicioDTO getByDescripcion(String descripcion);

}
