package cn.ripple.serviceimpl;

import cn.ripple.dao.PermissionDao;
import cn.ripple.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限接口实现
 * @author Edwin
 */
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public PermissionDao getRepository() {
        return permissionDao;
    }
}
