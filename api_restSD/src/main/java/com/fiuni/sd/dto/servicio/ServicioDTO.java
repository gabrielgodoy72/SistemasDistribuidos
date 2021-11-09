package com.fiuni.sd.dto.servicio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.fiuni.sd.dto.base.BaseDTO;

@XmlRootElement(name = "proveedores")
public class ServicioDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	private Integer id;
    private String descripcion;
    private Double costo;
    
    @XmlElement
	public Integer getId() {
		return id;
	}
    
	public void setId(Integer id) {
		this.id = id;
	}

	@XmlElement
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@XmlElement
	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Override
	public String toString() {
		return "ServicioDTO [id=" + id + ", descripcion=" + descripcion + ", costo=" + costo + "]";
	}
    
}