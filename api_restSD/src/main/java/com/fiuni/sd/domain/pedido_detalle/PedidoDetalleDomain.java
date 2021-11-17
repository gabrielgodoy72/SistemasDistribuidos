package com.fiuni.sd.domain.pedido_detalle;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fiuni.sd.domain.base.BaseDomain;
import com.fiuni.sd.domain.pedido.PedidoDomain;
import com.fiuni.sd.domain.servicio.ServicioDomain;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@Entity
@Table(name = "pedidos_detalle")
public class PedidoDetalleDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "fecha")
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private PedidoDomain pedido;

	@ManyToOne
	@JoinColumn(name = "servicio_id", nullable = false)
	private ServicioDomain servicio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public PedidoDomain getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDomain pedido) {
		this.pedido = pedido;
	}

	public ServicioDomain getServicio() {
		return servicio;
	}

	public void setServicio(ServicioDomain servicio) {
		this.servicio = servicio;
	}
	
	

}
