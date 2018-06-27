package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.MallAttribute;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商品属性表
 * @author Edwin
 */
@Mapper
public interface MallAttributeDAO extends IBaseDao {
    @Select("SELECT * FROM mall_attributeWHERE id= #{id}")
    MallAttribute fetch(@Param("id") String id);
}