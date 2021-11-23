package com.fiuni.sd.dto.cliente;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "clienteResult")
public class ClienteResult extends BaseResult<ClienteDTO> {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public List<ClienteDTO> getClientes() {
		return getList();
	}

	public void setClientes(List<ClienteDTO> dtos) {
		setList(dtos);
	}

}
