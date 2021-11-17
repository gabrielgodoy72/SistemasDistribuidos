package com.fiuni.sd.dto.cliente;

import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "clientes")
public class ClienteDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private String ci;

	private String nombre;

	private String direccion;

	private String email;

	private String telefono;

}
