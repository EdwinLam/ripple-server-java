package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.MallPackage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author Edwin
 */
@Mapper
public interface MallPackageDAO extends IBaseDao {
    @Select("SELECT * FROM mall_packageWHERE id= #{id}")
    MallPackage fetch(@Param("id") String id);
}