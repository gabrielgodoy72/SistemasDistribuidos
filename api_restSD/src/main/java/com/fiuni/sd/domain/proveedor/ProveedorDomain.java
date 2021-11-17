package com.fiuni.sd.domain.proveedor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fiuni.sd.domain.base.BaseDomain;
import com.fiuni.sd.domain.factura.compra.FacturaCompraDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "proveedores")
public class ProveedorDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "ruc")
	private String ruc;

	@Column(name = "telefono")
	private String telefono;

	@Column(name = "direccion")
	private String direccion;

	@OneToMany(mappedBy = "proveedor")
	private Set<FacturaCompraDomain> facturas_compra;

}
