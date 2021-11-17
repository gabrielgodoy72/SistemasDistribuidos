package com.fiuni.sd.dto.proveedor;

import javax.xml.bind.annotation.XmlRootElement;
import com.fiuni.sd.dto.base.BaseDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "proveedores")
public class ProveedorDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String nombre;

	private String ruc;

	private String telefono;

	private String direccion;

}
