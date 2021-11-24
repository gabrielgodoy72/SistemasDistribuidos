package com.sd.rest.facturaCompraDetalle;

import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.fiuni.sd.dto.producto.ProductoResult;
import com.sd.rest.base.BaseResourceImpl;
import com.sd.rest.facturaCompra.IFacturaCompraResource;
import org.springframework.stereotype.Repository;

@Repository("facturaCompraDetalleResource")
public class FacturaCompraDetalleResourceImpl extends BaseResourceImpl<FacturaCompraDetalleDTO> implements IFacturaCompraDetalleResource {

    public FacturaCompraDetalleResourceImpl() {
        super(FacturaCompraDetalleDTO.class, "/api/factura/compra");
    }

    @Override
    public FacturaCompraDetalleResult getAll(Integer page) {
        final FacturaCompraDetalleResult result = getWebResource().path("/detalle/page/"+page).get(FacturaCompraDetalleResult.class);
        return result;
    }

}