package com.sd.rest.cliente;

import com.fiuni.sd.dto.cliente.ClienteDTO;
import com.fiuni.sd.dto.cliente.ClienteResult;
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

@Repository("clienteResource")
public class ClienteResourceImpl extends BaseResourceImpl<ClienteDTO> implements IClienteResource {

    @Autowired
    private CacheManager cacheManager;

    public ClienteResourceImpl() {
        super(ClienteDTO.class, "/api");
    }

    @Override
    public ClienteResult getAll(Integer page) {
        final ClienteResult result = getWebResource().path("/clientes/page/" + page).get(ClienteResult.class);
        result.getClientes().forEach(cliente -> {
            cacheManager.getCache(Setting.CACHE_NAME).put("client_web_cliente_" + cliente.getId(), cliente);
        });
        return result;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_cliente_' + #result.getId()")
    public ClienteDTO save(ClienteDTO dto) {
        return getWebResource().path("/cliente").entity(dto).post(getDtoClass());
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_cliente_' + #id")
    public ClienteDTO getById(Integer id) {
        return getWebResource().path("/cliente/" + id).get(getDtoClass());
    }

    @Override
    @CacheEvict(value = Setting.CACHE_NAME, key = "'client_web_cliente_' + #id")
    public ClienteDTO delete(Integer id) {
        ClienteDTO cliente = getById(id);
        getWebResource().path("/cliente/" + id).delete();
        return cliente;
    }

    @Override
    @CachePut(value = Setting.CACHE_NAME, key = "'client_web_cliente_' + #id")
    public ClienteDTO update(Integer id, ClienteDTO dto) {
        cacheManager.getCache(Setting.CACHE_NAME).evict("client_web_cliente_" + id);
        return getWebResource().path("/cliente/" + id).entity(dto).put(getDtoClass());
    }

}