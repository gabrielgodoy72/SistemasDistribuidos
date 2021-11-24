package com.fiuni.sd.service.factura_detalle.compra;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.compra.FacturaCompraDetalleResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IFacturaCompraDetalleService extends IBaseService<FacturaCompraDetalleDTO, FacturaCompraDetalleResult> {

	public FacturaCompraDetalleDTO update(final Integer id, final FacturaCompraDetalleDTO dto);

	public FacturaCompraDetalleResult getAllByFactura(final Integer factura_id, final Pageable pageable);

	public FacturaCompraDetalleResult getAllByProducto(final Integer producto_id, final Pageable pageable);
}
