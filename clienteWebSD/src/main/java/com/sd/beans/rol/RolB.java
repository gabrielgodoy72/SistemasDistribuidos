package com.sd.beans.rol;

import com.sd.beans.base.BaseBean;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class RolB extends BaseBean {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String descripcion;

    public RolB(Map<String, String> params){
        super(params);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    protected void create(Map<String, String> params) {
        if (!StringUtils.isBlank(params.get("id"))) {
            setId(Integer.valueOf(params.get("id")));
        }
        setNombre(params.get("nombre"));
        setDescripcion(params.get("descripcion"));
    }
}
