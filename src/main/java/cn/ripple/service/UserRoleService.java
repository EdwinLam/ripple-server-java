package cn.ripple.service;

import cn.ripple.service.BaseService;
import cn.ripple.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户接口
 * @author Edwin
 */
public interface UserRoleService extends BaseService<UserRole,String> {
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
