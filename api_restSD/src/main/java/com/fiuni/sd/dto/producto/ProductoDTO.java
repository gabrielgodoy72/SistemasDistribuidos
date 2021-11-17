package com.fiuni.sd.dto.producto;

import javax.xml.bind.annotation.XmlRootElement;
import com.fiuni.sd.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "productos")
public class ProductoDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private String descripcion;
	
	private Double costo;

}