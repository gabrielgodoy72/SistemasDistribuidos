package com.fiuni.sd.dto.pedido;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@XmlRootElement(name = "pedidos")
public class PedidoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Boolean estado;

	@NotEmpty(message = "Client id could not be empty")
	private Integer id_cliente;

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	

}
