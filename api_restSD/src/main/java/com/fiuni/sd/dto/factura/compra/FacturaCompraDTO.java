package com.fiuni.sd.dto.factura.compra;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "factura_compra")
public class FacturaCompraDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private Date fecha;

	private String numero;

	private Double total;

	private Integer proveedor_id;
}
