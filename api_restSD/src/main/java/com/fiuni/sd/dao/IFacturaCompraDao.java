package com.fiuni.sd.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiuni.sd.domain.factura.compra.FacturaCompraDomain;
import com.fiuni.sd.domain.proveedor.ProveedorDomain;

@Repository
public interface IFacturaCompraDao extends JpaRepository<FacturaCompraDomain, Integer> {

	public Page<FacturaCompraDomain> findAll(final Pageable pageable);

	public Page<FacturaCompraDomain> findAllByFecha(final Date fecha, final Pageable pageable);

	public Optional<FacturaCompraDomain> findByNumero(final String numero);

	public Page<FacturaCompraDomain> findAllByProveedor(final ProveedorDomain proveedor, final Pageable pageable);
}
