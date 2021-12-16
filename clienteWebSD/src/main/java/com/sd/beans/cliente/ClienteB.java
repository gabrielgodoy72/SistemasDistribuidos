package com.sd.beans.cliente;

import java.util.Map;

import com.sd.beans.base.BaseBean;
import org.apache.commons.lang.StringUtils;

public class ClienteB extends BaseBean {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
    private String ci;

    public ClienteB(Map<String, String> params){
        super(params);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    @Override
    protected void create(Map<String, String> params) {
        if (!StringUtils.isBlank(params.get("id"))) {
            setId(Integer.valueOf(params.get("id")));
        }
        setNombre(params.get("nombre"));
        setDireccion(params.get("direccion"));
        setEmail(params.get("email"));
        setCi(params.get("ci"));
        setTelefono(params.get("telefono"));
    }
}
