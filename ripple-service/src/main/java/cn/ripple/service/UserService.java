package cn.ripple.service;

import cn.ripple.entity.User;

/**
 * @Author Edwin
 * @Description: 用户表接口
 */
public interface UserService extends BaseService<User, Long> {

    /**
     * 账号同步
     *
     * @param wechatId
     * @param user
     * @return
     */
    User synBasUser(String wechatId, User user);

    /**
     * 设置登录用户
     *
     * @param user
     */
    void setCurUser(User user);

    /**
     * 根据用户code获取用户
     * @param code
     * @return
     */
    User getUserByCode(String code);
}
