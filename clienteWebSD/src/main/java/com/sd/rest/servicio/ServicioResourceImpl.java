package com.sd.rest.servicio;

import com.fiuni.sd.dto.servicio.ServicioDTO;
import com.fiuni.sd.dto.servicio.ServicioResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.utils.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("servicioResource")
public class ServicioResourceImpl extends BaseResourceImpl<ServicioDTO> implements IServicioResource {

    @Autowired
    private CacheManager cacheManager;

    public ServicioResourceImpl() {
        super(ServicioDTO.class, "/api");
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_servicio_' + #result.getId()")
    public ServicioDTO save(ServicioDTO dto) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/servicio").entity(dto).post(getDtoClass());
    }

    @Override
    public ServicioResult getAll(Integer page) {
        setWebResourceBasicAuthFilter();
        final ServicioResult result = getWebResource().path("/servicios/page/" + page).get(ServicioResult.class);
        result.getServicios().forEach(servicio -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_servicio_" + servicio.getId(), servicio);
        });
        return result;
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_servicio_' + #id")
    public ServicioDTO getById(Integer id) {
        setWebResourceBasicAuthFilter();
        ServicioDTO servicioCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
                .get("client_web_servicio_" + id, getDtoClass());
        if (servicioCacheado != null) {
            return servicioCacheado;
        }
        return getWebResource().path("/servicio/" + id).get(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_servicio_' + #descripcion")
    public ServicioDTO getByDescripcion(String descripcion) {
        setWebResourceBasicAuthFilter();
        ServicioDTO servicioCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
                .get("client_web_servicio_" + descripcion, getDtoClass());
        if (servicioCacheado != null) {
            return servicioCacheado;
        }
        return getWebResource().path("/servicio/search/descripcion/" + descripcion).get(getDtoClass());
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_servicio_' + #id")
    public ServicioDTO delete(Integer id) {
        setWebResourceBasicAuthFilter();
        ServicioDTO servicio = getById(id);
        getWebResource().path("/servicio/" + id).delete();
        return servicio;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_servicio_' + #id")
    public ServicioDTO update(Integer id, ServicioDTO dto) {
        setWebResourceBasicAuthFilter();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_servicio_" + id);
        return getWebResource().path("/servicio/" + id).entity(dto).put(getDtoClass());
    }

}
