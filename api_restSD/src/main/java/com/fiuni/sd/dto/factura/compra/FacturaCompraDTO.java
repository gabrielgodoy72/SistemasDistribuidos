package com.fiuni.sd.dto.factura.compra;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@XmlRootElement(name = "factura_compra")
public class FacturaCompraDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Date fecha;

	private String numero;

	private Double total;

	private Integer proveedor_id;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getProveedor_id() {
		return proveedor_id;
	}

	public void setProveedor_id(Integer proveedor_id) {
		this.proveedor_id = proveedor_id;
	}
	
	
}
