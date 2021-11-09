package com.fiuni.sd.dto.cliente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

@XmlRootElement(name = "client")
public class ClientDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String ci;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
    
    @XmlElement
	public Integer getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public String getCi() {
		return ci;
	}
	
	public void setCi(String ci) {
		this.ci = ci;
	}
	
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElement
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	@XmlElement
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "ClientDTO [id=" + id + ", ci=" + ci + ", nombre=" + nombre + ", direccion=" + direccion + ", email="
				+ email + ", telefono=" + telefono + "]";
	}

}
