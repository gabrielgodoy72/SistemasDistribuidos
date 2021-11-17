package com.fiuni.sd.service.pedido;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.dto.pedido.PedidoDTO;
import com.fiuni.sd.dto.pedido.PedidoResult;
import com.fiuni.sd.service.base.IBaseService;

public interface IPedidoService extends IBaseService<PedidoDTO, PedidoResult> {

	public PedidoDTO update(final Integer id, final PedidoDTO dto);

	public PedidoResult getAllByCliente(final Integer idCliente, final Pageable pageable);
}
