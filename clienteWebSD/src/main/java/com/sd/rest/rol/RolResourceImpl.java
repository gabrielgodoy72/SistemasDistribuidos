package com.sd.rest.rol;

import com.fiuni.sd.dto.rol.RolDTO;
import com.fiuni.sd.dto.rol.RolResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.utils.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("rolResource")
public class RolResourceImpl extends BaseResourceImpl<RolDTO> implements IRolResource {

    @Autowired
    private CacheManager cacheManager;

    public RolResourceImpl() {
        super(RolDTO.class, "/account");
    }

    @Override
    public RolResult getAll(Integer page) {
        final RolResult result = getWebResource().path("/roles/page/" + page).get(RolResult.class);
        result.getRoles().forEach(rol -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_rol_" + rol.getId(), rol);
        });
        return result;
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_rol_' + #nombre")
    public RolDTO getByNombre(String nombre) {
        return getWebResource().path("/rol/search/nombre/" + nombre).get(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_rol_' + #descripcion")
    public RolDTO getByDescripcion(String descripcion) {
        return getWebResource().path("/rol/search/descripcion/" + descripcion).get(getDtoClass());
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_rol_' + #result.getId()")
    public RolDTO save(RolDTO dto) {
        return getWebResource().path("/rol").entity(dto).post(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_rol_' + #id")
    public RolDTO getById(Integer id) {
        return getWebResource().path("/rol/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        getWebResource().path("/rol/" + id).delete();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_rol_" + id);
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_rol_' + #id")
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_rol_' + #id")
    public RolDTO update(Integer id, RolDTO dto) {
        return getWebResource().path("/rol/" + id).entity(dto).put(getDtoClass());
    }

}
