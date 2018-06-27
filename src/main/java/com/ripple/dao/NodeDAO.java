package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.Node;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 节点记录表
 * @author Edwin
 */
@Mapper
public interface NodeDAO extends IBaseDao {
    @Select("SELECT * FROM nodeWHERE id= #{id}")
    Node fetch(@Param("id") String id);
}