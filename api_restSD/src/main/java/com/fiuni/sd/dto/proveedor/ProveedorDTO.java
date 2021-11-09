package com.fiuni.sd.dto.proveedor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.fiuni.sd.dto.base.BaseDTO;

@XmlRootElement(name = "proveedores")
public class ProveedorDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String nombre;
    private String ruc;
    private String telefono;
    private String direccion;
    
    @XmlElement
	public Integer getId() {
		return id;
	}
    
	public void setId(Integer id) {
		this.id = id;
	}
	
	@XmlElement
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@XmlElement
	public String getRuc() {
		return ruc;
	}
	
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	
	@XmlElement
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@XmlElement
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "ProveedorDTO [id=" + id + ", nombre=" + nombre + ", ruc=" + ruc + ", telefono=" + telefono
				+ ", direccion=" + direccion + "]";
	}
    
}
