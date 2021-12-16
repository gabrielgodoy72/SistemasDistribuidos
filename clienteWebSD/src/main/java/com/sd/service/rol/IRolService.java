package com.sd.service.rol;

import com.fiuni.sd.dto.rol.RolDTO;
import com.sd.beans.rol.RolB;
import com.sd.service.base.IBaseService;

public interface IRolService extends IBaseService<RolB, RolDTO> {

    public RolB getRolByNombre(String nombre);

    public RolB getRolByDescripcion(String descripcion);

}
