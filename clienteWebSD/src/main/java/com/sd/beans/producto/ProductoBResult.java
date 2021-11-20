package com.sd.beans.producto;

import com.sd.beans.base.BaseBResult;

import java.util.List;

public class ProductoBResult extends BaseBResult<ProductoB> {

    private static final long serialVersionUID = 1L;

    public List<ProductoB> getProductos() {
        return getList();
    }

    public void setProductos(List<ProductoB> dtos) {
        super.setList(dtos);
    }
}
