package com.fiuni.sd.service.factura_detalle.venta;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleDTO;
import com.fiuni.sd.dto.factura_detalle.venta.FacturaVentaDetalleResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IFacturaVentaDetalleService extends IBaseService<FacturaVentaDetalleDTO, FacturaVentaDetalleResult> {

	public FacturaVentaDetalleDTO update(final Integer id, final FacturaVentaDetalleDTO dto);

	public FacturaVentaDetalleResult getAllByFactura(final Integer idFactura, final Pageable pageable);

	public FacturaVentaDetalleResult getAllByServicio(final Integer idServicio, final Pageable pageable);
}
