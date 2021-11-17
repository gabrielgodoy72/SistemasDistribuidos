package com.fiuni.sd.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.pedido_detalle.PedidoDetalleDomain;

@Repository
public interface IPedidoDetalleDao extends JpaRepository<PedidoDetalleDomain, Integer> {

	public Page<PedidoDetalleDomain> findAll(final Pageable pageable);

	public Page<PedidoDetalleDomain> findAllByPedido(final Integer idPedido, final Pageable pageable);

	public Page<PedidoDetalleDomain> findAllByServicio(final Integer idServicio, final Pageable pageable);

	public Page<PedidoDetalleDomain> findAllByFecha(final Date fecha, final Pageable pageable);

}
