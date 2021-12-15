package com.fiuni.sd.service.rol;

import com.fiuni.sd.dto.rol.RolDTO;
import com.fiuni.sd.dto.rol.RolResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IRolService extends IBaseService<RolDTO, RolResult> {

	public RolDTO update(final Integer id, final RolDTO dto);

	public RolDTO getByNombre(final String nombre);

	public RolDTO getByDescripcion(final String descripcion);

}
