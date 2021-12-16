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
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #result.getId()")
    public FacturaCompraDTO save(FacturaCompraDTO dto) {
        setWebResourceBasicAuthFilter();
        return getWebResource().path("/compra").entity(dto).post(getDtoClass());
    }

    @Override
    public FacturaCompraResult getAll(Integer page) {
        setWebResourceBasicAuthFilter();
        final FacturaCompraResult result = getWebResource().path("/compra/page/" + page).get(FacturaCompraResult.class);
        result.getFacturasCompra().forEach(facturaCompra -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_facturaCompra_" + facturaCompra.getId(), facturaCompra);
        });
        return result;
    }

    @Override
    public FacturaCompraResult getAllByProveedor(Integer idProveedor, Integer page) {
        setWebResourceBasicAuthFilter();
        final FacturaCompraResult result = getWebResource().path("/compra/search/proveedor/" + idProveedor + "/page/" + page).get(FacturaCompraResult.class);
        result.getFacturasCompra().forEach(facturaCompra -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_facturaCompra_" + facturaCompra.getId(), facturaCompra);
        });
        return result;
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #id")
    public FacturaCompraDTO getById(Integer id) {
        setWebResourceBasicAuthFilter();
        FacturaCompraDTO facturaCacheada = cacheManager.getCache(Setting.CACHE_NAME)//
                .get("client_web_facturaCompra_" + id, getDtoClass());
        if (facturaCacheada != null) {
            return facturaCacheada;
        }
        return getWebResource().path("/compra/" + id).get(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #numero")
    public FacturaCompraDTO getByNumero(String numero) {
        setWebResourceBasicAuthFilter();
        FacturaCompraDTO facturaCacheada = cacheManager.getCache(Setting.CACHE_NAME)//
                .get("client_web_facturaCompra_" + numero, getDtoClass());
        if (facturaCacheada != null) {
            return facturaCacheada;
        }
        return getWebResource().path("/compra/search/numero/" + numero).get(getDtoClass());
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #id")
    public FacturaCompraDTO delete(Integer id) {
        setWebResourceBasicAuthFilter();
        FacturaCompraDTO facturaCompra = getById(id);
        getWebResource().path("/compra/" + id).delete();
        return facturaCompra;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_facturaCompra_' + #id")
    public FacturaCompraDTO update(Integer id, FacturaCompraDTO dto) {
        setWebResourceBasicAuthFilter();
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_facturaCompra_" + id);
        return getWebResource().path("/compra/" + id).entity(dto).put(getDtoClass());
    }

}