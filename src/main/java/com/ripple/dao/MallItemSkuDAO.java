package com.ripple.dao;

import com.ripple.base.IBaseDao;
import com.ripple.entity.MallItemSku;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author Edwin
 */
@Mapper
public interface MallItemSkuDAO extends IBaseDao {
    @Select("SELECT * FROM mall_item_skuWHERE id= #{id}")
    MallItemSku fetch(@Param("id") String id);
}