package com.sd.rest.proveedor;

import com.fiuni.sd.dto.proveedor.ProveedorDTO;
import com.fiuni.sd.dto.proveedor.ProveedorResult;
import com.sd.rest.base.IBaseResource;

public interface IProveedorResource extends IBaseResource<ProveedorDTO> {

    public ProveedorResult getAll(Integer page);

}
