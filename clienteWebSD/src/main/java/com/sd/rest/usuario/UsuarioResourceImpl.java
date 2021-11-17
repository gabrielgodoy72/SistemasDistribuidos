package com.sd.rest.usuario;

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.sd.rest.base.BaseResourceImpl;
import org.springframework.stereotype.Repository;

@Repository("usuarioResource")
public class UsuarioResourceImpl extends BaseResourceImpl<UsuarioDTO> implements IUsuarioResource {

    public UsuarioResourceImpl() {
        super(UsuarioDTO.class, "/api");
    }

    @Override
    public UsuarioResult getAll(Integer page) {
        final UsuarioResult result = getWebResource().path("/usuarios/page/" + page).get(UsuarioResult.class);
        return result;
    }
}
