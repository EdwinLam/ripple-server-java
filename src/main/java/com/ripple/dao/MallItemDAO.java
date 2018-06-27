package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.MallItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author Edwin
 */
@Mapper
public interface MallItemDAO extends IBaseDao {
    @Select("SELECT * FROM mall_itemWHERE id= #{id}")
    MallItem fetch(@Param("id") String id);
}