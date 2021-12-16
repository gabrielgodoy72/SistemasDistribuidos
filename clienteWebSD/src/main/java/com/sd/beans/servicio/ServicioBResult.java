package com.sd.beans.servicio;

import com.sd.beans.base.BaseBResult;

import java.util.List;

public class ServicioBResult extends BaseBResult<ServicioB> {

    private static final long serialVersionUID = 1L;

    public List<ServicioB> getServicios() {
        return getList();
    }

    public void setServicios(List<ServicioB> beans) {
        super.setList(beans);
    }
}
