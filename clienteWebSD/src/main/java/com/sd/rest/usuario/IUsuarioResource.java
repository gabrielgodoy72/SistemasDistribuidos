package com.sd.rest.usuario;

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.sd.rest.base.IBaseResource;

public interface IUsuarioResource extends IBaseResource<UsuarioDTO> {

    public UsuarioResult getAll(Integer page);

    public UsuarioDTO getByUsername(String username);

}
