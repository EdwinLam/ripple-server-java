package cn.ripple.service;

import cn.ripple.common.vo.SearchVo;
import cn.ripple.service.BaseService;
import cn.ripple.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户接口
 * @author Edwin
 */
//@CacheConfig(cacheNames = "user")
public interface UserService extends BaseService<User,String> {
    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
//    @Cacheable(key = "#username")
    User findByUsername(String username);

    /**
     * 多条件分页获取用户
     * @param user
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<User> findByCondition(User user, SearchVo searchVo, Pageable pageable);
}