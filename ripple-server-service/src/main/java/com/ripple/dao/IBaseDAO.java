package com.ripple.dao;

import com.ripple.domain.BaseDomain;

public interface IBaseDAO<T extends BaseDomain> {
    /**
     * 根据id进行对象查询
     *
     * @param id
     * @return
     */
    T fetch(Object id);
}
