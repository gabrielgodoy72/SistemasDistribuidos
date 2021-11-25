package com.fiuni.sd.dto.producto;

import javax.xml.bind.annotation.XmlRootElement;
import com.fiuni.sd.dto.base.BaseDTO;

@XmlRootElement(name = "productos")
public class ProductoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private String descripcion;
	
	private Double costo;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}
	
	

}