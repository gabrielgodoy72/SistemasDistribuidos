package com.fiuni.sd.dto.factura.venta;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "factura_venta")
public class FacturaVentaDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Date fecha;

	private String numero;

	private Double total;

	private Integer cliente_id;
}
