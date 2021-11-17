package com.fiuni.sd.dto.usuario;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "usuarioResult")
public class UsuarioResult extends BaseResult<UsuarioDTO> {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<UsuarioDTO> getUsers() {
		return getList();
	}

	public void setUsers(final List<UsuarioDTO> dtos) {
		super.setList(dtos);
	}
}
