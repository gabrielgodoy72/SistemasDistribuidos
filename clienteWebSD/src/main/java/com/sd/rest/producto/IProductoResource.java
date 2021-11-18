package com.sd.rest.producto;

import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.sd.rest.base.IBaseResource;

public interface IProductoResource extends IBaseResource<ProductoDTO> {

    public ProductoResult getAll(Integer page);

}
