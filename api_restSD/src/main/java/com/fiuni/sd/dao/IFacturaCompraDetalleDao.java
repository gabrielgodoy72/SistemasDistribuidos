package com.fiuni.sd.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.factura.compra.FacturaCompraDomain;
import com.fiuni.sd.domain.factura_detalle.compra.FacturaCompraDetalleDomain;
import com.fiuni.sd.domain.producto.ProductoDomain;

@Repository
public interface IFacturaCompraDetalleDao extends JpaRepository<FacturaCompraDetalleDomain, Integer> {

	public Page<FacturaCompraDetalleDomain> findAll(final Pageable pageable);

	public Page<FacturaCompraDetalleDomain> findAllByProducto(final ProductoDomain producto, final Pageable pageable);

	public Page<FacturaCompraDetalleDomain> findAllByFactura(final FacturaCompraDomain factura, final Pageable pageable);
}
