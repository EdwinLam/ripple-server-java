package com.ripple.dao.base;

import mybatis.generator.BaseModel;

import java.util.List;

public interface BaseDao<T extends BaseModel> {

    List<T> selectAll();

    int delete(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T fetch(Integer id);

    int update(T record);
}