package com.sd.rest.facturaCompra;

import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.utils.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("facturaCompraResource")
public class FacturaCompraResourceImpl extends BaseResourceImpl<FacturaCompraDTO> implements IFacturaCompraResource {

    @Autowired
    private CacheManager cacheManager;

    public FacturaCompraResourceImpl() {
        super(FacturaCompraDTO.class, "/api/factura");
    }

    @Override
    public FacturaCompraResult getAll(Integer page) {
        setWebResourceBasicAuthFilter();
        final FacturaCompraResult result = getWebResource().path("/compra/page/" + page).get(FacturaCompraResult.class);
        result.getFacturasCompra().forEach(proveedor -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_facturaCompra_" + proveedor.getId(), proveedor);
        });
        return result;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #result.getId()")
    public FacturaCompraDTO save(FacturaCompraDTO dto) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/compra").entity(dto).post(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #id")
    public FacturaCompraDTO getById(Integer id) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/compra/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        setWebResourceBasicAuthFilter();
        getWebResource().path("/compra/" + id).delete();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_facturaCompra_" + id);
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #id")
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #id")
    public FacturaCompraDTO update(Integer id, FacturaCompraDTO dto) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/compra/" + id).entity(dto).put(getDtoClass());
    }

}