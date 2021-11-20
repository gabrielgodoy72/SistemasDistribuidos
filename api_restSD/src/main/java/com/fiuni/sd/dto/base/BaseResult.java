package com.fiuni.sd.dto.base;

import java.io.Serializable;
import java.util.List;

public abstract class BaseResult<DTO extends BaseDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<DTO> _dtos;
	private Integer _page;
	private Integer _totalPages;

	protected List<DTO> getList() {
		return _dtos;
	}

	protected void setList(final List<DTO> dtos) {
		_dtos = dtos;
	}

	public Integer getTotal() {
		return null == _dtos ? 0 : _dtos.size();
	}

	public Integer getTotalPages() {
		return _totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		_totalPages = totalPages;
	}

	public Integer getPage() {
		return _page;
	}

	public void setPage(Integer page) {
		_page = page;
	}

}
