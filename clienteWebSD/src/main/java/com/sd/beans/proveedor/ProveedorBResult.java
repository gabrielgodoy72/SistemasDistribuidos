package com.sd.beans.proveedor;

import com.sd.beans.base.BaseBResult;

import java.util.List;

public class ProveedorBResult extends BaseBResult<ProveedorB> {

    private static final long serialVersionUID = 1L;

    public List<ProveedorB> getProveedores() {
        return getList();
    }

    public void setProveedores(List<ProveedorB> beans) {
        super.setList(beans);
    }
}
