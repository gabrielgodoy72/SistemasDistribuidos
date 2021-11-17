package com.sd.rest.base;

import com.fiuni.sd.dto.base.BaseDTO;

public interface IBaseResource<DTO extends BaseDTO> {

    public DTO save(DTO dto, String path);

    public DTO getById(Integer id, String path);

}
