package cn.ripple.serviceimpl.mybaits;


import cn.ripple.dao.mapper.UserRoleMapper;
import cn.ripple.entity.Role;
import cn.ripple.entity.UserRole;
import cn.ripple.service.mybatis.IUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Exrickx
 */
@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserId(String userId) {
        return userRoleMapper.findByUserId(userId);
    }
}
