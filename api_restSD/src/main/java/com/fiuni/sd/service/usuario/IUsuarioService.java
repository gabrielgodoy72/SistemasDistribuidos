package com.fiuni.sd.service.usuario;

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IUsuarioService extends IBaseService<UsuarioDTO, UsuarioResult> {

	public UsuarioDTO update(final Integer id, final UsuarioDTO dto);

}
