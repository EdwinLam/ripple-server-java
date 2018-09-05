package cn.ripple.dao;

import cn.ripple.dao.BaseDao;
import cn.ripple.entity.User;

import java.util.List;

/**
 * 用户数据处理层
 * @author Edwin
 */
public interface UserDao extends BaseDao<User,String> {
    /**
     * 通过用户名和状态获取用户
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameAndStatus(String username, Integer status);

}