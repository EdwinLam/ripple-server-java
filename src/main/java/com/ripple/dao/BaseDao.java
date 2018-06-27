package com.ripple.dao;

import com.ripple.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {

    List<T> selectAll();

    int delete(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T fetch(Integer id);

    int update(T record);
}