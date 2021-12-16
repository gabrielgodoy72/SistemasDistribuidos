package com.sd.service.facturaCompraDetalle;

import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleB;
import com.sd.beans.facturaCompraDetalle.FacturaCompraDetalleBResult;
import com.sd.service.base.IBaseService;

public interface IFacturaCompraDetalleService extends IBaseService<FacturaCompraDetalleB, FacturaCompraDetalleDTO> {

    public FacturaCompraDetalleBResult getAllByFactura(Integer idFactura, Integer page);

}
