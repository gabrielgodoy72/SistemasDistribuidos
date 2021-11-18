package com.sd.rest.producto;

import com.fiuni.sd.dto.producto.ProductoDTO;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.sd.rest.base.BaseResourceImpl;
import org.springframework.stereotype.Repository;

@Repository("productoResource")
public class ProductoResourceImpl extends BaseResourceImpl<ProductoDTO> implements IProductoResource {

    public ProductoResourceImpl() {
        super(ProductoDTO.class, "/api");
    }

    @Override
    public ProductoResult getAll(Integer page) {
        final ProductoResult result = getWebResource().path("/productos/page/" + page).get(ProductoResult.class);
        return result;
    }

}
