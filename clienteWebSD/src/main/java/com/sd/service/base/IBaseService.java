package com.sd.service.base;

import com.fiuni.sd.dto.base.BaseDTO;
import com.sd.beans.base.BaseBResult;
import com.sd.beans.base.BaseBean;

public interface IBaseService<BEAN extends BaseBean, DTO extends BaseDTO> {

    public BEAN save(BEAN bean);

    public BaseBResult<BEAN> getAll(Integer page);

    public BEAN getById(Integer id);

    public void delete(Integer id);

    public BEAN update(Integer id, BEAN bean);

}
