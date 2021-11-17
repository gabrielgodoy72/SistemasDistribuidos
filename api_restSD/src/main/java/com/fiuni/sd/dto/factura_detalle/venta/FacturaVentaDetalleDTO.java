package com.fiuni.sd.dto.factura_detalle.venta;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@XmlRootElement(name = "factura_venta_detalle")
public class FacturaVentaDetalleDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Double subtotal;

	private Integer cantidad;

	private Integer servicio_id;

	private Integer factura_id;

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getServicio_id() {
		return servicio_id;
	}

	public void setServicio_id(Integer servicio_id) {
		this.servicio_id = servicio_id;
	}

	public Integer getFactura_id() {
		return factura_id;
	}

	public void setFactura_id(Integer factura_id) {
		this.factura_id = factura_id;
	}
	
	

}
