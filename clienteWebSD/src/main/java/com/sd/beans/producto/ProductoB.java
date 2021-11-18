package com.sd.beans.producto;

import com.sd.beans.base.BaseBean;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class ProductoB extends BaseBean {

    private static final long serialVersionUID = 1L;
    private String descripcion;
    private Double costo;

    public ProductoB(Map<String, String> params){
        super(params);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    @Override
    protected void create(Map<String, String> params) {
        if (!StringUtils.isBlank(params.get("id"))) {
            setId(Integer.valueOf(params.get("id")));
        }
        setDescripcion(params.get("descripcion"));
        if (!StringUtils.isBlank(params.get("costo"))) {
            setCosto(Double.valueOf(params.get("costo")));
        }
    }
}
