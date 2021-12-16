package com.sd.beans.cliente;

import com.sd.beans.base.BaseBResult;

import java.util.List;

public class ClienteBResult extends BaseBResult<ClienteB> {

    private static final long serialVersionUID = 1L;

    public List<ClienteB> getClientes() {
        return getList();
    }

    public void setClientes(List<ClienteB> beans) {
        super.setList(beans);
    }
}
