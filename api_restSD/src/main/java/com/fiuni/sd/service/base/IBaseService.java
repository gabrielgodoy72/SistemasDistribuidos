package com.fiuni.sd.service.base;

import org.springframework.data.domain.Pageable;

import com.fiuni.sd.dto.base.BaseDTO;
import com.fiuni.sd.dto.base.BaseResult;

public interface IBaseService<DTO extends BaseDTO, R extends BaseResult<DTO>> {
	public DTO save(DTO dto);
	public DTO getById(Integer id);
	public R getAll(Pageable pageable);
	public void deleteById(int id);
}
