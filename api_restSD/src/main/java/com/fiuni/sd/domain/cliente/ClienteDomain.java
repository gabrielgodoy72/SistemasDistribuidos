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

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set<FacturaVentaDomain> getFacturas_venta() {
		return facturas_venta;
	}

	public void setFacturas_venta(Set<FacturaVentaDomain> facturas_venta) {
		this.facturas_venta = facturas_venta;
	}

	public Set<PedidoDomain> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<PedidoDomain> pedidos) {
		this.pedidos = pedidos;
	}
	
	

}
