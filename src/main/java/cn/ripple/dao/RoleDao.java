package cn.ripple.dao;

import cn.ripple.dao.BaseDao;
import cn.ripple.entity.Role;

import java.util.List;

/**
 * 角色权限数据处理层
 * @author Edwin
 */
public interface RoleDao extends BaseDao<Role,String> {
    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
