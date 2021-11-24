package com.sd.beans.facturaCompraDetalle;

import com.sd.beans.base.BaseBResult;

import java.util.List;

public class FacturaCompraDetalleBResult extends BaseBResult<FacturaCompraDetalleB> {

    private static final long serialVersionUID = 1L;

    public List<FacturaCompraDetalleB> getFacturaCompraDetalle() {
        return getList();
    }

    public void setFacturasCompraDetalle(List<FacturaCompraDetalleB> dtos) {
        super.setList(dtos);
    }
    //setFacturasCompraDetalle
}
