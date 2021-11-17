package com.fiuni.sd.service.pedido_detalle;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleDTO;
import com.fiuni.sd.dto.pedido_detalle.PedidoDetalleResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IPedidoDetalleService extends IBaseService<PedidoDetalleDTO, PedidoDetalleResult> {

	public PedidoDetalleDTO update(final Integer id, final PedidoDetalleDTO dto);

	public PedidoDetalleResult getAllByPedido(final Integer idPedido, final Pageable pageable);

	public PedidoDetalleResult getAllByServicio(final Integer idServicio, final Pageable pageable);

	public PedidoDetalleResult getAllByFecha(final Date fecha, final Pageable pageable);
}
