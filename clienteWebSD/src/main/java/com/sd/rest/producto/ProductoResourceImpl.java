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

    @Override
    public ProductoDTO save(ProductoDTO dto) {
        return getWebResource().path("/producto").entity(dto).post(getDtoClass());
    }

    @Override
    public ProductoDTO getById(Integer id) {
        return getWebResource().path("/producto/" + id).get(getDtoClass());
    }

    @Override
    public void delete(Integer id) {
        getWebResource().path("/producto/" + id).delete();
    }

    @Override
    public ProductoDTO update(Integer id, ProductoDTO dto) {
        return getWebResource().path("/producto/" + id).entity(dto).put(getDtoClass());
    }
}
