package com.fiuni.sd.domain.cliente;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fiuni.sd.domain.base.BaseDomain;
import com.fiuni.sd.domain.factura.venta.FacturaVentaDomain;
import com.fiuni.sd.domain.pedido.PedidoDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class ClienteDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "ci")
	private String ci;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "email")
	private String email;

	@Column(name = "telefono")
	private String telefono;

	@OneToMany(mappedBy = "cliente")
	private Set<FacturaVentaDomain> facturas_venta;

	@OneToMany(mappedBy = "cliente")
	private Set<PedidoDomain> pedidos;

}
