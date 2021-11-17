package com.fiuni.sd.dto.factura_detalle.compra;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "factura_compra_detalle")
public class FacturaCompraDetalleDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Double subtotal;

	private Integer cantidad;

	private Integer producto_id;

	private Integer factura_id;

}
