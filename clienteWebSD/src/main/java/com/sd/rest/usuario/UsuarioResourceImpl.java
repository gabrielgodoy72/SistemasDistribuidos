package com.sd.rest.usuario;

import com.fiuni.sd.dto.usuario.UsuarioDTO;
import com.fiuni.sd.dto.usuario.UsuarioResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.utils.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("usuarioResource")
public class UsuarioResourceImpl extends BaseResourceImpl<UsuarioDTO> implements IUsuarioResource {

    @Autowired
    private CacheManager cacheManager;

    public UsuarioResourceImpl() {
        super(UsuarioDTO.class, "/account");
    }

    @Override
    public UsuarioResult getAll(Integer page) {
        final UsuarioResult result = getWebResource().path("/usuarios/page/" + page).get(UsuarioResult.class);
        result.getUsers().forEach(usuario -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_usuario_" + usuario.getId(), usuario);
        });
        return result;
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #username")
    public UsuarioDTO getByUsername(String username) {
        UsuarioDTO usuarioCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
                .get("client_web_usuario_" + username, getDtoClass());
        if (usuarioCacheado != null) {
            return usuarioCacheado;
        }
        return getWebResource().path("/usuario/search/username/" + username).get(getDtoClass());
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #result.getId()")
    public UsuarioDTO save(UsuarioDTO dto) {
        return getWebResource().path("/register").entity(dto).post(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #id")
    public UsuarioDTO getById(Integer id) {
        UsuarioDTO usuarioCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
                .get("client_web_usuario_" + id, getDtoClass());
        if (usuarioCacheado != null) {
            return usuarioCacheado;
        }
        return getWebResource().path("/usuario/" + id).get(getDtoClass());
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #id")
    public UsuarioDTO delete(Integer id) {
        UsuarioDTO usuario = getById(id);
        getWebResource().path("/usuario/" + id).delete();
        return usuario;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #id")
    public UsuarioDTO update(Integer id, UsuarioDTO dto) {
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_usuario_" + id);
        return getWebResource().path("/usuario/" + id).entity(dto).put(getDtoClass());
    }

}
