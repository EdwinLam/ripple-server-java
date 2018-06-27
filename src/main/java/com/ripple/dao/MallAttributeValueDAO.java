package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.MallAttributeValue;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author Edwin
 */
@Mapper
public interface MallAttributeValueDAO extends IBaseDao {
    @Select("SELECT * FROM mall_attribute_valueWHERE id= #{id}")
    MallAttributeValue fetch(@Param("id") String id);
}