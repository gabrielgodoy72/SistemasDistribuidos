package com.sd.beans.usuario;

import com.sd.beans.base.BaseBResult;

import java.util.List;

public class UsuarioBResult extends BaseBResult<UsuarioB> {

    private static final long serialVersionUID = 1L;

    public List<UsuarioB> getUsuarios() {
        return getList();
    }

    public void setUsuarios(List<UsuarioB> dtos) {
        super.setList(dtos);
    }
}
