package com.ripple.dao;

import com.ripple.domain.account.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/24.
 */
@Mapper
public interface IAccountDAO{
    String tableName = "account";
    String columnNames ="id,accountName";
    @SelectProvider(type = AccountProvider.class, method = "fetch")
    Account fetch(@Param("id") Long id);
    @SelectProvider(type = AccountProvider.class, method = "list")
    List<Account> list(@Param("condition") Map<String,Object> condition);

    class AccountProvider {
        public String fetch(Map<String,Object> param) {
            return new SQL(){{
                SELECT(columnNames);
                FROM(tableName);
                WHERE("id = #{id}");
            }}.toString();
        }
        /*
        * 字段操作：like = > <
        *
        *
        *
        * */
        public String list(Map<String,Object> param){
            return "";
        }
        public String page(){
            return "";
        }
    }
}