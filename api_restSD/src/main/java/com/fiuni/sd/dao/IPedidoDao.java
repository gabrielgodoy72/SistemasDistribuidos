package com.fiuni.sd.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.cliente.ClienteDomain;
import com.fiuni.sd.domain.pedido.PedidoDomain;

@Repository
public interface IPedidoDao extends JpaRepository<PedidoDomain, Integer> {

	public Page<PedidoDomain> findAll(final Pageable pageable);

	public Page<PedidoDomain> findAllByCliente(final ClienteDomain cliente, final Pageable pageable);

}
