package cn.ripple.dao;

import cn.ripple.dao.BaseDao;
import cn.ripple.entity.UserRole;

import java.util.List;

/**
 * 用户数据处理层
 * @author Edwin
 */
public interface UserRoleDao extends BaseDao<UserRole,String> {
    /**
     * 通过roleId查找
     * @param roleId
     * @return
     */
    List<UserRole> findByRoleId(String roleId);

    /**
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(String userId);
}
