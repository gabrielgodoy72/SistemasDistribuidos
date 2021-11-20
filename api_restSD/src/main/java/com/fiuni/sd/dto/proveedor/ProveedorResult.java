package com.fiuni.sd.dto.proveedor;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "proveedorResult")
public class ProveedorResult extends BaseResult<ProveedorDTO> {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public List<ProveedorDTO> getProveedores() {
		return getList();
	}

	public void setProveedores(List<ProveedorDTO> dtos) {
		super.setList(dtos);
	}

}
