package com.fiuni.sd.domain.factura_detalle.venta;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fiuni.sd.domain.base.BaseDomain;
import com.fiuni.sd.domain.factura.venta.FacturaVentaDomain;
import com.fiuni.sd.domain.servicio.ServicioDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "factura_venta_detalle")
public class FacturaVentaDetalleDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "subtotal")
	private Double subtotal;

	@Column(name = "cantidad")
	private Integer cantidad;

	@ManyToOne
	@JoinColumn(name = "servicio_id", nullable = false)
	private ServicioDomain servicio;

	@ManyToOne
	@JoinColumn(name = "factura_id", nullable = false)
	private FacturaVentaDomain factura;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public ServicioDomain getServicio() {
		return servicio;
	}

	public void setServicio(ServicioDomain servicio) {
		this.servicio = servicio;
	}

	public FacturaVentaDomain getFactura() {
		return factura;
	}

	public void setFactura(FacturaVentaDomain factura) {
		this.factura = factura;
	}

}
