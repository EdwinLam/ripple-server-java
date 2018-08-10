package cn.ripple.service;

import cn.ripple.service.BaseService;
import cn.ripple.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色权限接口
 * @author Edwin
 */
public interface RoleService extends BaseService<Role,String> {

}