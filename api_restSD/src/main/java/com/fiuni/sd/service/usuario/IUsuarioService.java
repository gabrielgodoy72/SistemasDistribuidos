package com.fiuni.sd.service.usuario;

import com.fiuni.sd.domain.credenciales.UsuarioDomain;
import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioDTO2;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IUsuarioService extends IBaseService<UsuarioDTO, UsuarioResult>{
	public UsuarioResult getAllUsers();
	public UsuarioDTO saveFirst(UsuarioDTO2 dto2);
	public UsuarioDomain convertoDto2ToDomain(UsuarioDTO2 dto2);
}
