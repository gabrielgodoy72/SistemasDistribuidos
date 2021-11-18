package com.sd.beans.proveedor;

import java.util.Map;

import com.sd.beans.base.BaseBean;
import org.apache.commons.lang.StringUtils;

public class ProveedorB extends BaseBean {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String ruc;
    private String telefono;
    private String direccion;

    public ProveedorB(Map<String, String> params){
        super(params);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRuc() { return ruc; }
    public void setRuc(String ruc) { this.ruc = ruc; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    protected void create(Map<String, String> params) {
        if (!StringUtils.isBlank(params.get("id"))) {
            setId(Integer.valueOf(params.get("id")));
        }
        setNombre(params.get("nombre"));
        setRuc(params.get("ruc"));
        setTelefono(params.get("telefono"));
        setDireccion(params.get("direccion"));
    }
}
