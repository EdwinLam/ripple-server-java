package cn.ripple.service;

import cn.ripple.entity.BasUser;

/**
 * @Author Edwin
 * @Description: 用户基本信息接口
 */
public interface BasUserService extends BaseService<BasUser,Long> {
     /**
      * 账号同步
      * @param openid 微信唯一id
      * @param basUser 用户信息
      * @return
      */
     BasUser synBasUser(String openid);


}
