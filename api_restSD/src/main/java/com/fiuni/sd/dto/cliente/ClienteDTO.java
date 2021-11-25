package com.fiuni.sd.dto.cliente;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

@XmlRootElement(name = "clientes")
public class ClienteDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private String ci;

	private String nombre;

	private String direccion;

	private String email;

	private String telefono;

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
	
	

}
