package com.sd.rest.proveedor;

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.sd.rest.base.BaseResourceImpl;
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

}
