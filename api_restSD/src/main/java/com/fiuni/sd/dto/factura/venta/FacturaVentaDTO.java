package com.fiuni.sd.dto.factura.venta;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@XmlRootElement(name = "factura_venta")
public class FacturaVentaDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Date fecha;

	private String numero;

	private Double total;

	private Integer cliente_id;

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

	public Integer getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}
	
	
}
