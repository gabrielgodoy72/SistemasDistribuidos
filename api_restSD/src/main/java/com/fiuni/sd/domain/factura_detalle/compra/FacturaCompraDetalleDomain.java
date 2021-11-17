package com.fiuni.sd.domain.factura_detalle.compra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fiuni.sd.domain.base.BaseDomain;
import com.fiuni.sd.domain.factura.compra.FacturaCompraDomain;
import com.fiuni.sd.domain.producto.ProductoDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "factura_compra_detalle")
public class FacturaCompraDetalleDomain implements BaseDomain {

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
	@JoinColumn(name = "producto_id", nullable = false)
	private ProductoDomain producto;

	@ManyToOne
	@JoinColumn(name = "factura_id", nullable = false)
	private FacturaCompraDomain factura;

}
