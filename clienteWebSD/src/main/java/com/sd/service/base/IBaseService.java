package com.sd.service.base;

import com.fiuni.sd.dto.base.BaseDTO;
import com.sd.beans.base.BaseBean;

import java.util.List;

public interface IBaseService<BEAN extends BaseBean, DTO extends BaseDTO> {

    public BEAN save(BEAN bean);

    public List<BEAN> getAll(Integer page);

    public BEAN getById(Integer id);

    public void delete(Integer id);

    public BEAN update(Integer id, BEAN bean);

}
