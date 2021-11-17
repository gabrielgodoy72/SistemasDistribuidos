package com.sd.service.base;

import com.fiuni.sd.dto.base.BaseDTO;
import com.sd.beans.base.BaseBean;

public abstract class BaseServiceImpl<BEAN extends BaseBean, DTO extends BaseDTO> implements IBaseService<BEAN, DTO> {

    public BaseServiceImpl(){

    }

    protected abstract BEAN convertDtoToBean(DTO dto);

    protected abstract DTO convertBeanToDto(BEAN bean);

}
