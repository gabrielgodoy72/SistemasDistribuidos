package com.fiuni.sd.dto.pedido;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "pedidoResult")
public class PedidoResult extends BaseResult<PedidoDTO> {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<PedidoDTO> getPedidos() {
		return getList();
	}

	public void setPedidos(List<PedidoDTO> dtos) {
		setList(dtos);
	}

}
