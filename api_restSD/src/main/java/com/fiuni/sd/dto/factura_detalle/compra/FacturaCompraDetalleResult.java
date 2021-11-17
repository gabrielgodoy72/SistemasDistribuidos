package com.fiuni.sd.dto.factura_detalle.compra;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fiuni.sd.dto.base.BaseResult;

@XmlRootElement(name = "facturaCompraDetalleResult")
public class FacturaCompraDetalleResult extends BaseResult<FacturaCompraDetalleDTO> {

	private static final long serialVersionUID = 1L;

	@XmlElement
	public List<FacturaCompraDetalleDTO> getFacturasCompraDetalle() {
		return getList();
	}

	public void setFacturasCompraDetalle(List<FacturaCompraDetalleDTO> dtos) {
		setFacturasCompraDetalle(dtos);
	}
}
