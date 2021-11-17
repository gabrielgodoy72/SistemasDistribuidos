package com.fiuni.sd.domain.servicio;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fiuni.sd.domain.base.BaseDomain;
import com.fiuni.sd.domain.factura_detalle.venta.FacturaVentaDetalleDomain;
import com.fiuni.sd.domain.pedido_detalle.PedidoDetalleDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "servicios")
public class ServicioDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "costo")
	private Double costo;

	@OneToMany(mappedBy = "servicio")
	private Set<PedidoDetalleDomain> detalles;

	@OneToMany(mappedBy = "servicio")
	private Set<FacturaVentaDetalleDomain> ventas_detalles;

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

	public Set<PedidoDetalleDomain> getDetalles() {
		return detalles;
	}

	public void setDetalles(Set<PedidoDetalleDomain> detalles) {
		this.detalles = detalles;
	}

	public Set<FacturaVentaDetalleDomain> getVentas_detalles() {
		return ventas_detalles;
	}

	public void setVentas_detalles(Set<FacturaVentaDetalleDomain> ventas_detalles) {
		this.ventas_detalles = ventas_detalles;
	}
	
	

}