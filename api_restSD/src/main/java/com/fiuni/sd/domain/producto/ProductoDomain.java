package com.fiuni.sd.domain.producto;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fiuni.sd.domain.base.BaseDomain;
import com.fiuni.sd.domain.factura_detalle.compra.FacturaCompraDetalleDomain;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@Entity
@Table(name = "productos")
public class ProductoDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "costo")
	private Double costo;

	@OneToMany(mappedBy = "producto")
	private Set<FacturaCompraDetalleDomain> facturas_compra_detalle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Set<FacturaCompraDetalleDomain> getFacturas_compra_detalle() {
		return facturas_compra_detalle;
	}

	public void setFacturas_compra_detalle(Set<FacturaCompraDetalleDomain> facturas_compra_detalle) {
		this.facturas_compra_detalle = facturas_compra_detalle;
	}
	

}
