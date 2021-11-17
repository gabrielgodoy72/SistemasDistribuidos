package com.fiuni.sd.dto.factura.compra;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "facturaCompraResult")
public class FacturaCompraResult extends BaseResult<FacturaCompraDTO> {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<FacturaCompraDTO> getFacturaCompra() {
		return getList();
	}

	public void setFacturasCompra(List<FacturaCompraDTO> dtos) {
		setFacturasCompra(dtos);
	}
}
