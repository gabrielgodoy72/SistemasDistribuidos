package com.sd.beans.usuario;

import java.util.Map;

import com.sd.beans.base.BaseBean;
import org.apache.commons.lang.StringUtils;

public class UsuarioB extends BaseBean {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private String email;
    private String password;

    public UsuarioB(Map<String, String> params){
        super(params);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected void create(Map<String, String> params) {
        if (!StringUtils.isBlank(params.get("id"))) {
            setId(Integer.valueOf(params.get("id")));
        }
        setNombre(params.get("nombre"));
        setApellido(params.get("apellido"));
        setEmail(params.get("email"));
        setPassword(params.get("password"));
    }
}
