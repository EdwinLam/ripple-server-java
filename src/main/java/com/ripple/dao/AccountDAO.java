package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 账号表
 * @author Edwin
 */
@Mapper
public interface AccountDAO extends IBaseDao {
    @Select("SELECT * FROM accountWHERE id= #{id}")
    Account fetch(@Param("id") String id);
}