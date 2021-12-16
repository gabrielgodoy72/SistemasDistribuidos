package com.sd.beans.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseBResult<BEAN extends BaseBean> implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<BEAN> _beans;
    private Integer total; // total de beans en la bd
    private Integer totalPages;
    private Integer page = 0;
    private Boolean hasPrev;
    private Boolean hasNext;
    private Integer prevPage;
    private Integer nextPage;

    public BaseBResult() {
    }

    public List<BEAN> getList() {
        return this._beans;
    }

    public void setList(final List<BEAN> beans) {
        this._beans = beans;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer numberOfElements) {
        this.total = numberOfElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getNextPage() {
        return this.nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public void setPrevPage(Integer prevPage) {
        this.prevPage = prevPage;
    }

    public Integer getPrevPage() {
        return this.prevPage;
    }

    public Boolean getHasPrev() {
        return hasPrev;
    }

    public void setHasPrev(Boolean hasPrev) {
        this.hasPrev = hasPrev;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }
}
