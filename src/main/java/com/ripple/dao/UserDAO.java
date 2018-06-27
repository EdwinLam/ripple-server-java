package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author Edwin
 */
@Mapper
public interface UserDAO extends IBaseDao {
    @Select("SELECT * FROM userWHERE id= #{id}")
    User fetch(@Param("id") String id);
}