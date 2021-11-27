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
        super(UsuarioDTO.class, "/api");
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
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #result.getId()")
    public UsuarioDTO save(UsuarioDTO dto) {
        return getWebResource().path("/usuario").entity(dto).post(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #id")
    public UsuarioDTO getById(Integer id) {
        return getWebResource().path("/usuario/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        getWebResource().path("/usuario/" + id).delete();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_usuario_" + id);
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #id")
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_usuario_' + #id")
    public UsuarioDTO update(Integer id, UsuarioDTO dto) {
        return getWebResource().path("/usuario/" + id).entity(dto).put(getDtoClass());
    }

}
