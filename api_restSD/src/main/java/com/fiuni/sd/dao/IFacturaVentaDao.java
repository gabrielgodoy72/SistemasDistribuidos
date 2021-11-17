package com.fiuni.sd.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.factura.venta.FacturaVentaDomain;

@Repository
public interface IFacturaVentaDao extends JpaRepository<FacturaVentaDomain, Integer> {

	public Page<FacturaVentaDomain> findAll(final Pageable pageable);

	public Page<FacturaVentaDomain> findAllByFecha(final Date fecha, final Pageable pageable);

	public Optional<FacturaVentaDomain> findByNumero(final String numero);

	public Page<FacturaVentaDomain> findAllByCliente(final Integer idCliente, final Pageable pageable);
}
