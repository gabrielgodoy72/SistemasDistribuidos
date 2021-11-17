package com.fiuni.sd.domain.factura.venta;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fiuni.sd.domain.base.BaseDomain;
import com.fiuni.sd.domain.cliente.ClienteDomain;
import com.fiuni.sd.domain.factura_detalle.venta.FacturaVentaDetalleDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "factura_venta")
public class FacturaVentaDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "numero")
	private String numero;

	@Column(name = "total")
	private Double total;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private ClienteDomain cliente;

	@OneToMany(mappedBy = "factura")
	private Set<FacturaVentaDetalleDomain> detalles;
	
}
