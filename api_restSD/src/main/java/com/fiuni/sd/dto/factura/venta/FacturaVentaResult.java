package com.fiuni.sd.dto.factura.venta;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "facturaVentaResult")
public class FacturaVentaResult extends BaseResult<FacturaVentaDTO> {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<FacturaVentaDTO> getFacturasVenta() {
		return getList();
	}

	public void setFacturasVenta(List<FacturaVentaDTO> dtos) {
		setFacturasVenta(dtos);
	}
}
