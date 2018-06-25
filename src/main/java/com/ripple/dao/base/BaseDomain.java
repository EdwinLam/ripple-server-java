package com.ripple.dao.base;

import java.io.Serializable;

public class BaseDomain<T> implements Serializable {
    private T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
