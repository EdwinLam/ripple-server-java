package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.App;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author Edwin
 */
@Mapper
public interface AppDAO extends IBaseDao {
    @Select("SELECT * FROM appWHERE id= #{id}")
    App fetch(@Param("id") String id);
}