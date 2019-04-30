package cn.ripple.controller;

import cn.ripple.entity.BasUser;
import cn.ripple.entity.GroupUserRel;
import cn.ripple.utils.UserInfoRedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: Edwin
 * @Description: 基础的Controller类
 */
public abstract class BaseController {

    @Autowired
    private UserInfoRedisUtils userInfoRedisUtils;

    protected BasUser getCurrentUser(){
        Long userId = (Long) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return userInfoRedisUtils.getUserById(userId);
    }

    protected Long getCurrentGroupId(){
        return  userInfoRedisUtils.getCurGroupRel(getCurrentUser().getId()).getGroupId();
    }

    protected GroupUserRel getCurrentGroupRel(){
        return  userInfoRedisUtils.getCurGroupRel(getCurrentUser().getId());
    }


    protected Long getCurrentUserId(){
        return  getCurrentUser().getId();
    }
}
