package com.sd.rest.producto;

import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.utils.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("productoResource")
public class ProductoResourceImpl extends BaseResourceImpl<ProductoDTO> implements IProductoResource {

    @Autowired
    private CacheManager cacheManager;

    public ProductoResourceImpl() {
        super(ProductoDTO.class, "/api");
    }

    @Override
    public ProductoResult getAll(Integer page) {
        setWebResourceBasicAuthFilter();
        final ProductoResult result = getWebResource().path("/productos/page/" + page).get(ProductoResult.class);
        result.getProductos().forEach(producto -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_producto_" + producto.getId(), producto);
        });
        return result;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_producto_' + #result.getId()")
    public ProductoDTO save(ProductoDTO dto) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/producto").entity(dto).post(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_producto_' + #id")
    public ProductoDTO getById(Integer id) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/producto/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        setWebResourceBasicAuthFilter();
        getWebResource().path("/producto/" + id).delete();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_producto_" + id);
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_producto_' + #id")
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_producto_' + #id")
    public ProductoDTO update(Integer id, ProductoDTO dto) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/producto/" + id).entity(dto).put(getDtoClass());
    }

}
