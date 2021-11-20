package com.sd.beans.facturaCompra;

import com.sd.beans.base.BaseBean;
import com.sd.beans.proveedor.ProveedorB;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class FacturaCompraB extends BaseBean {

    private static final long serialVersionUID = 1L;
    private Date fecha;
    private String numero;
    private Double total = 0.0;
    private ProveedorB proveedor;

    public FacturaCompraB(Map<String, String> params) { super(params); }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public ProveedorB getProveedor() { return proveedor; }
    public void setProveedor(ProveedorB proveedor) { this.proveedor = proveedor; }

    @Override
    protected void create(Map<String, String> params) {
        if (!StringUtils.isBlank(params.get("id"))) {
            setId(Integer.valueOf(params.get("id")));
        }
        setNumero(params.get("numero"));
        if (!StringUtils.isBlank(params.get("total"))) {
            setTotal(Double.valueOf(params.get("total")));
        }
    }
}
