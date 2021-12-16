package com.fiuni.sd.dto.factura_detalle.compra;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

@XmlRootElement(name = "factura_compra_detalle")
public class FacturaCompraDetalleDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Double subtotal;

	private Integer cantidad;

	private Integer producto_id;

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

	public Integer getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(Integer producto_id) {
		this.producto_id = producto_id;
	}

	public Integer getFactura_id() {
		return factura_id;
	}

	public void setFactura_id(Integer factura_id) {
		this.factura_id = factura_id;
	}

}
