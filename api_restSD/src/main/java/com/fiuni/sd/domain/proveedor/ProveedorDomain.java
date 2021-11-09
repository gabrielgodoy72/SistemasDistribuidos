package com.fiuni.sd.domain.proveedor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fiuni.sd.domain.base.BaseDomain;

@Entity
@Table(name = "proveedores")
public class ProveedorDomain implements BaseDomain {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
    private int id;
	
	@Column(name = "nombre")
    private String nombre;

    @Column(name = "ruc")
    private String ruc;

    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "direccion")
    private String direccion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "ProveedorDomain [id=" + id + ", nombre=" + nombre + ", ruc=" + ruc + ", telefono=" + telefono
				+ ", direccion=" + direccion + "]";
	}
    
}
