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

@Repository("facturaCompraDetalleResource")
public class FacturaCompraDetalleResourceImpl extends BaseResourceImpl<FacturaCompraDetalleDTO> implements IFacturaCompraDetalleResource {

    @Autowired
    private CacheManager cacheManager;

    public FacturaCompraDetalleResourceImpl() {
        super(FacturaCompraDetalleDTO.class, "/api/factura/compra");
    }

    @Override
    public FacturaCompraDetalleResult getAll(Integer page) {
        final FacturaCompraDetalleResult result = getWebResource().path("/detalle/page/"+page).get(FacturaCompraDetalleResult.class);
        result.getFacturasCompraDetalle().forEach(proveedor -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_facturaCompraDetalle_" + proveedor.getId(), proveedor);
        });
        return result;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_facturaCompraDetalle_' + #result.getId()")
    public FacturaCompraDetalleDTO save(FacturaCompraDetalleDTO dto) {
        return getWebResource().path("/detalle").entity(dto).post(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_facturaCompraDetalle_' + #id")
    public FacturaCompraDetalleDTO getById(Integer id) {
        return getWebResource().path("/detalle/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        getWebResource().path("/detalle/" + id).delete();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_facturaCompraDetalle_" + id);
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_facturaCompraDetalle_' + #id")
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_facturaCompraDetalle_' + #id")
    public FacturaCompraDetalleDTO update(Integer id, FacturaCompraDetalleDTO dto) {
        return getWebResource().path("/detalle/" + id).entity(dto).put(getDtoClass());
    }

}