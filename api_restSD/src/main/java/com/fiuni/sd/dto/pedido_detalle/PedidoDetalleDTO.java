package com.fiuni.sd.dto.pedido_detalle;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "pedido_detalle")
public class PedidoDetalleDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Date fecha;

	private Integer pedido_id;

	private Integer servicio_id;

}
