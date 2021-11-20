package com.fiuni.sd.dto.servicio;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "servicioResult")
public class ServicioResult extends BaseResult<ServicioDTO> {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	public List<ServicioDTO> getServicio() {
		return getList();
	}

	public void setServicios(List<ServicioDTO> dtos) {
		super.setList(dtos);
	}

}
