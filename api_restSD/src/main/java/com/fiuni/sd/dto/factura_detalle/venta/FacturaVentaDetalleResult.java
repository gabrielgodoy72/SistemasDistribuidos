package com.fiuni.sd.dto.factura_detalle.venta;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "facturaVentaDetalleResult")
public class FacturaVentaDetalleResult extends BaseResult<FacturaVentaDetalleDTO> {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<FacturaVentaDetalleDTO> getFacturasVentaDetalle() {
		return getList();
	}

	public void setFacturasVentaDetalle(List<FacturaVentaDetalleDTO> dtos) {
		setFacturasVentaDetalle(dtos);
	}
}
