package com.sd.rest.base;

import com.fiuni.sd.dto.base.BaseDTO;

public interface IBaseResource<DTO extends BaseDTO> {

    public DTO save(DTO dto);

    public DTO getById(Integer id);

    public DTO delete(Integer id);

    public DTO update(Integer id, DTO dto);

}
