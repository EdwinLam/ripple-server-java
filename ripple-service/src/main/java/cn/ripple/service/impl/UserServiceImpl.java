package cn.ripple.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.ripple.constant.UserInfoRedis;
import cn.ripple.dao.UserDao;
import cn.ripple.entity.User;
import cn.ripple.enums.AppCodeEnum;
import cn.ripple.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author Edwin
 * @Description: 微信相关服务
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDao getRepository() {
        return userDao;
    }

    @Override
    public User synBasUser(String wechatId,User user) {
        User userParam = new User();
        userParam.setWechatId(wechatId);
        User dbUser =findOne(userParam);
        if(dbUser==null){
            user.setWechatId(wechatId);
            user.setCode(RandomUtil.simpleUUID());
            dbUser = save(user);
        }
        return dbUser;
    }

    @Override
    public void setCurUser(User user){
        String redisKey = AppCodeEnum.RIPPLE.getValue()+":" + UserInfoRedis.CUR_USER+":"+user.getCode();
        redisTemplate.expire(redisKey, 60, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(redisKey,user);
    }

    @Override
    public User getUserByCode(String code){
        String redisKey = AppCodeEnum.RIPPLE.getValue()+":" + UserInfoRedis.CUR_USER+":"+code;
        User user = (User) redisTemplate.opsForValue().get(redisKey);
        return user;
    }
}
