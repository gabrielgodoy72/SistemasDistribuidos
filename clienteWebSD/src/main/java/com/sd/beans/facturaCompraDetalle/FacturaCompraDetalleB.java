package com.sd.beans.facturaCompraDetalle;

import com.sd.beans.base.BaseBean;
import com.sd.beans.facturaCompra.FacturaCompraB;
import com.sd.beans.producto.ProductoB;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class FacturaCompraDetalleB extends BaseBean {

    private static final long serialVersionUID = 1L;
    private Integer cantidad;
    private Double subtotal;
    private FacturaCompraB factura;
    private ProductoB producto;

    public FacturaCompraDetalleB(Map<String, String> params) { super(params); }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public FacturaCompraB getFactura() { return factura; }
    public void setFactura(FacturaCompraB factura) { this.factura = factura; }

    public ProductoB getProducto() { return producto; }
    public void setProducto(ProductoB producto) { this.producto = producto; }

    @Override
    protected void create(Map<String, String> params) {
        if (!StringUtils.isBlank(params.get("id"))) {
            setId(Integer.valueOf(params.get("id")));
        }
        if (!StringUtils.isBlank(params.get("cantidad"))) {
            setCantidad(Integer.valueOf(params.get("cantidad")));
        }
    }
}
