package cn.ripple.service;

import cn.ripple.service.BaseService;
import cn.ripple.entity.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 权限接口
 * @author Edwin
 */
public interface PermissionService extends BaseService<Permission,String> {

}