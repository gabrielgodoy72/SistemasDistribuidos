package com.sd.rest.facturaCompraDetalle;

import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.utils.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository("facturaCompraDetalleResource")
public class FacturaCompraDetalleResourceImpl extends BaseResourceImpl<FacturaCompraDetalleDTO> implements IFacturaCompraDetalleResource {

    @Autowired
    private CacheManager cacheManager;

    public FacturaCompraDetalleResourceImpl() {
        super(FacturaCompraDetalleDTO.class, "/api/factura/compra");
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_facturaCompraDetalle_' + #result.getId()")
    public FacturaCompraDetalleDTO save(FacturaCompraDetalleDTO dto) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/detalle").entity(dto).post(getDtoClass());
    }

    @Override
    public FacturaCompraDetalleResult getAll(Integer page) {
        setWebResourceBasicAuthFilter();
        final FacturaCompraDetalleResult result = getWebResource().path("/detalle/page/"+page).get(FacturaCompraDetalleResult.class);
        result.getFacturasCompraDetalle().forEach(proveedor -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_facturaCompraDetalle_" + proveedor.getId(), proveedor);
        });
        return result;
    }

    @Override
    public FacturaCompraDetalleResult getAllByFactura(Integer idFactura, Integer page) {
        setWebResourceBasicAuthFilter();
        final FacturaCompraDetalleResult result = getWebResource().path("/detalle/search/factura/" + idFactura + "/page/" + page).get(FacturaCompraDetalleResult.class);
        if(result.getFacturasCompraDetalle() == null){
            result.setFacturasCompraDetalle(Collections.emptyList());
        } else {
            result.getFacturasCompraDetalle().forEach(proveedor -> {
                cacheManager.getCache(Setting.CACHE_NAME).put("client_web_facturaCompraDetalle_" + proveedor.getId(), proveedor);
            });
        }
        return result;
    }

    @Override
    public FacturaCompraDetalleResult getAllByProducto(Integer idProducto, Integer page) {
        setWebResourceBasicAuthFilter();
        final FacturaCompraDetalleResult result = getWebResource().path("/detalle/search/producto/" + idProducto + "/page/" + page).get(FacturaCompraDetalleResult.class);
        result.getFacturasCompraDetalle().forEach(proveedor -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_facturaCompraDetalle_" + proveedor.getId(), proveedor);
        });
        return result;
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_facturaCompraDetalle_' + #id")
    public FacturaCompraDetalleDTO getById(Integer id) {
        setWebResourceBasicAuthFilter();
        FacturaCompraDetalleDTO detalleCacheado = cacheManager.getCache(Setting.CACHE_NAME)//
                .get("client_web_facturaCompraDetalle_" + id, getDtoClass());
        if (detalleCacheado != null) {
            return detalleCacheado;
        }
        return getWebResource().path("/detalle/" + id).get(getDtoClass());
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_facturaCompraDetalle_' + #id")
    public FacturaCompraDetalleDTO delete(Integer id) {
        setWebResourceBasicAuthFilter();
        FacturaCompraDetalleDTO facturaCompraDetalle = getById(id);
        getWebResource().path("/detalle/" + id).delete();
        return facturaCompraDetalle;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_facturaCompraDetalle_' + #id")
    public FacturaCompraDetalleDTO update(Integer id, FacturaCompraDetalleDTO dto) {
        setWebResourceBasicAuthFilter();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_facturaCompraDetalle_" + id);
        return getWebResource().path("/detalle/" + id).entity(dto).put(getDtoClass());
    }

}