package com.ripple.dao;

import com.ripple.domain.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/24.
 */
@Mapper
public interface IAccountExDAO extends IAccountDAO {

    @SelectProvider(type = AccountProviderEx.class, method = "fetch")
    Account test(@Param("id") Long id);

    class AccountProviderEx {
        public String fetch(Map<String,Object> param) {
            return new SQL(){{
                SELECT(columnNames);
                FROM(tableName);
                WHERE("id = #{id}");
            }}.toString();
        }
    }
}