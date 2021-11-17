package com.fiuni.sd.domain.pedido;

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
import com.fiuni.sd.domain.pedido_detalle.PedidoDetalleDomain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class PedidoDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "estado")
	private Boolean estado;

	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false)
	private ClienteDomain cliente;

	@OneToMany(mappedBy = "pedido")
	private Set<PedidoDetalleDomain> detalles;
	
}
