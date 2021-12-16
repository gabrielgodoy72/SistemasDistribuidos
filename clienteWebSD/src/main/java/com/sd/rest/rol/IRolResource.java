package com.sd.rest.rol;

import com.fiuni.sd.dto.rol.RolDTO;
import com.fiuni.sd.dto.rol.RolResult;
import com.sd.rest.base.IBaseResource;

public interface IRolResource extends IBaseResource<RolDTO> {

    public RolResult getAll(Integer page);

    public RolDTO getByNombre(String nombre);

    public RolDTO getByDescripcion(String descripcion);

}
