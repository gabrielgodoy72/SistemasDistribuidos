package com.sd.beans.facturaCompra;

import com.sd.beans.base.BaseBResult;

import java.util.List;

public class FacturaCompraBResult extends BaseBResult<FacturaCompraB> {

    private static final long serialVersionUID = 1L;

    public List<FacturaCompraB> getFacturaCompra() {
        return getList();
    }

    public void setFacturaCompra(List<FacturaCompraB> dtos) {
        super.setList(dtos);
    }
}
