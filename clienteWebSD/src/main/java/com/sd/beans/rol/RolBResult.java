package com.sd.beans.rol;

import com.sd.beans.base.BaseBResult;

import java.util.List;

public class RolBResult extends BaseBResult<RolB> {

    private static final long serialVersionUID = 1L;

    public List<RolB> getRoles() {
        return getList();
    }

    public void setRoles(List<RolB> dtos) {
        super.setList(dtos);
    }
}
