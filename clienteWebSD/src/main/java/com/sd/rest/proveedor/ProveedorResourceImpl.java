package com.sd.rest.proveedor;

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.utils.Setting;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("proveedorResource")
public class ProveedorResourceImpl extends BaseResourceImpl<ProveedorDTO> implements IProveedorResource {

    public ProveedorResourceImpl() {
        super(ProveedorDTO.class, "/api");
    }

    @Override
    public ProveedorResult getAll(Integer page) {
        final ProveedorResult result = getWebResource().path("/proveedores/page/" + page).get(ProveedorResult.class);
        return result;
    }

    @Override
    @Cacheable(value = Setting.CACHE_NAME, key = "'client_web_proveedor_' + #result.getId()")
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
    }

    @Override
    public ProveedorDTO update(Integer id, ProveedorDTO dto) {
        return getWebResource().path("/proveedor/" + id).entity(dto).put(getDtoClass());
    }

}
