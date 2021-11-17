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

}