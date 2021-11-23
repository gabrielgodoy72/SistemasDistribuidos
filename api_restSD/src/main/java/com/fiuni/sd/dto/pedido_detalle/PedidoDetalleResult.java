package com.fiuni.sd.dto.pedido_detalle;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "pedidoDetalleResult")
public class PedidoDetalleResult extends BaseResult<PedidoDetalleDTO> {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<PedidoDetalleDTO> getPedidosDetalle() {
		return getList();
	}

	public void setPedidosDetalle(List<PedidoDetalleDTO> dtos) {
		setList(dtos);
	}

}
