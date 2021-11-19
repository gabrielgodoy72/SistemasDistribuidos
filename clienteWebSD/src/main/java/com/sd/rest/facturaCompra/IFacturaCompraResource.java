package com.sd.rest.facturaCompra;

import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.sd.rest.base.IBaseResource;

public interface IFacturaCompraResource extends IBaseResource<FacturaCompraDTO> {

    public FacturaCompraResult getAll(Integer page);

}
