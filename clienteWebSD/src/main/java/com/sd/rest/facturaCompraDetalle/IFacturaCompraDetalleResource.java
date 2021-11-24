package com.sd.rest.facturaCompraDetalle;

import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.sd.rest.base.IBaseResource;

public interface IFacturaCompraDetalleResource extends IBaseResource<FacturaCompraDetalleDTO> {

    public FacturaCompraDetalleResult getAll(Integer page);

}
