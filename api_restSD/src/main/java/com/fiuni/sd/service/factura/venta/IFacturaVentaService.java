package com.fiuni.sd.service.factura.venta;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.dto.factura.venta.FacturaVentaDTO;
import com.fiuni.sd.dto.factura.venta.FacturaVentaResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IFacturaVentaService extends IBaseService<FacturaVentaDTO, FacturaVentaResult> {

	public FacturaVentaDTO update(final Integer id, final FacturaVentaDTO dto);

	public FacturaVentaResult getAllByFecha(final Date fecha, final Pageable pageable);

	public FacturaVentaDTO getByNumero(final String numero);

	public FacturaVentaResult getAllByCliente(final Integer idCliente, final Pageable pageable);
}
