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

    @Override
    public UsuarioDTO save(UsuarioDTO dto) {
        return getWebResource().path("/usuario").entity(dto).post(getDtoClass());
    }

    @Override
    public UsuarioDTO getById(Integer id) {
        return getWebResource().path("/usuario/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        getWebResource().path("/usuario/" + id).delete();
    }

    @Override
    public UsuarioDTO update(Integer id, UsuarioDTO dto) {
        return getWebResource().path("/usuario/" + id).entity(dto).put(getDtoClass());
    }
}
