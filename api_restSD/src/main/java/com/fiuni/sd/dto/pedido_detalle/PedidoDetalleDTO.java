package com.fiuni.sd.dto.pedido_detalle;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@XmlRootElement(name = "pedido_detalle")
public class PedidoDetalleDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Date fecha;

	private Integer pedido_id;

	private Integer servicio_id;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getPedido_id() {
		return pedido_id;
	}

	public void setPedido_id(Integer pedido_id) {
		this.pedido_id = pedido_id;
	}

	public Integer getServicio_id() {
		return servicio_id;
	}

	public void setServicio_id(Integer servicio_id) {
		this.servicio_id = servicio_id;
	}
	
	

}
