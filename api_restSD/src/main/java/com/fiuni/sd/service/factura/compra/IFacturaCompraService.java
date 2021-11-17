package com.fiuni.sd.service.factura.compra;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.dto.factura.compra.FacturaCompraDTO;
import com.fiuni.sd.dto.factura.compra.FacturaCompraResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IFacturaCompraService extends IBaseService<FacturaCompraDTO, FacturaCompraResult> {

	public FacturaCompraDTO update(final Integer id, final FacturaCompraDTO dto);

	public FacturaCompraResult getAllByFecha(final Date fecha, final Pageable pageable);

	public FacturaCompraDTO getByNumero(final String numero);

	public FacturaCompraResult getAllByProveedor(final Integer idProveedor, final Pageable pageable);
}
