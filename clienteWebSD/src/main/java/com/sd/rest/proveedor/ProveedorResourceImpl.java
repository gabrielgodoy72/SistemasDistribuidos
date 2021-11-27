package com.sd.rest.proveedor;

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.utils.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("proveedorResource")
public class ProveedorResourceImpl extends BaseResourceImpl<ProveedorDTO> implements IProveedorResource {

    @Autowired
    private CacheManager cacheManager;

    public ProveedorResourceImpl() {
        super(ProveedorDTO.class, "/api");
    }

    @Override
    public ProveedorResult getAll(Integer page) {
        final ProveedorResult result = getWebResource().path("/proveedores/page/" + page).get(ProveedorResult.class);
        result.getProveedores().forEach(proveedor -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_proveedor_" + proveedor.getId(), proveedor);
        });
        return result;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_proveedor_' + #result.getId()")
    public ProveedorDTO save(ProveedorDTO dto) {
        return getWebResource().path("/proveedor").entity(dto).post(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_proveedor_' + #id")
    public ProveedorDTO getById(Integer id) {
        return getWebResource().path("/proveedor/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        getWebResource().path("/proveedor/" + id).delete();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_proveedor_" + id);
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_proveedor_' + #id")
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_proveedor_' + #id")
    public ProveedorDTO update(Integer id, ProveedorDTO dto) {
        return getWebResource().path("/proveedor/" + id).entity(dto).put(getDtoClass());
    }

}