package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.MallType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author Edwin
 */
@Mapper
public interface MallTypeDAO extends IBaseDao {
    @Select("SELECT * FROM mall_typeWHERE id= #{id}")
    MallType fetch(@Param("id") String id);
}