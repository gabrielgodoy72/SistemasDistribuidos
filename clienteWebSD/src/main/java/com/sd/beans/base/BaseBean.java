package com.sd.beans.base;

import java.io.Serializable;
import java.util.Map;
import java.util.Random;

public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer _id;
    private final Random _random;

    public BaseBean(Map<String, String> params) {
        _random = new Random();
        create(params);
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer id) {
        _id = id;
    }

    protected Integer generateId() {
        return _random.nextInt(1000);
    }

    protected abstract void create(Map<String, String> params);

}
