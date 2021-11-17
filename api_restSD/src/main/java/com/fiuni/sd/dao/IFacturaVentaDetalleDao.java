package com.fiuni.sd.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.factura_detalle.venta.FacturaVentaDetalleDomain;

@Repository
public interface IFacturaVentaDetalleDao extends JpaRepository<FacturaVentaDetalleDomain, Integer> {

	public Page<FacturaVentaDetalleDomain> findAll(final Pageable pageable);

	public Page<FacturaVentaDetalleDomain> findAllByServicio(final Integer idServicio, final Pageable pageable);

	public Page<FacturaVentaDetalleDomain> findAllByFactura(final Integer idFactura, final Pageable pageable);
}
