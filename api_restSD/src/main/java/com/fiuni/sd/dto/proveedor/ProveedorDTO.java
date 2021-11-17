package com.fiuni.sd.dto.proveedor;

import javax.xml.bind.annotation.XmlRootElement;
import com.fiuni.sd.dto.base.BaseDTO;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@XmlRootElement(name = "proveedores")
public class ProveedorDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String nombre;

	private String ruc;

	private String telefono;

	private String direccion;

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

}
