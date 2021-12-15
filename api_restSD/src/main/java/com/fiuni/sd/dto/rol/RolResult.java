package com.fiuni.sd.dto.rol;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "rolesResult")
public class RolResult extends BaseResult<RolDTO> {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public List<RolDTO> getRoles() {
		return getList();
	}

	public void setRoles(List<RolDTO> dtos) {
		setList(dtos);
	}

}
