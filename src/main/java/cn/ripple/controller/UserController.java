package cn.ripple.controller;

import cn.hutool.core.util.StrUtil;
import cn.ripple.common.utils.PageUtil;
import cn.ripple.common.utils.ResultUtil;
import cn.ripple.common.vo.PageVo;
import cn.ripple.common.vo.Result;
import cn.ripple.common.vo.SearchVo;
import cn.ripple.entity.Role;
import cn.ripple.entity.User;
import cn.ripple.entity.UserRole;
import cn.ripple.service.RoleService;
import cn.ripple.service.UserRoleService;
import cn.ripple.service.UserService;
import cn.ripple.service.mybatis.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Edwin
 */
@Slf4j
@RestController
@Api(description = "用户管理接口")
@RequestMapping("/ripple/user")
@CacheConfig(cacheNames = "user")
@Transactional
public class UserController extends BaseController<User, String>{

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IUserRoleService iUserRoleService;


    @Override
    public UserService getService() {
        return userService;
    }

    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取用户列表")
    public Result<Page<User>> getByCondition(@ModelAttribute User user,
                                             @ModelAttribute SearchVo searchVo,
                                             @ModelAttribute PageVo pageVo){

        Page<User> page = userService.findByCondition(user, searchVo, PageUtil.initPage(pageVo));
        for(User u: page.getContent()){
            List<Role> list = iUserRoleService.findByUserId(u.getId());
            u.setRoles(list);
//            u.setPassword(null);
        }
        return new ResultUtil<Page<User>>().setData(page);
    }


    /**
     * @param u
     * @param roles
     * @return
     */
    @RequestMapping(value = "/admin/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改资料",notes = "需要通过id获取原用户信息 需要username更新缓存")
    @CacheEvict(key = "#u.username")
    public Result<Object> edit(@ModelAttribute User u,
                               @RequestParam(required = false) String[] roles){
        User old = userService.get(u.getId());
        // 密码为空则使用旧密码
        if(u.getPassword().equals(""))
            u.setPassword(old.getPassword());
        //所修改了用户名
        if(!old.getUsername().equals(u.getUsername())){
            //若修改用户名删除原用户名缓存
            redisTemplate.delete("user::"+old.getUsername());
            //判断新用户名是否存在
            if(userService.findByUsername(u.getUsername())!=null){
                return new ResultUtil<Object>().setErrorMsg("该用户名已被存在");
            }
            //删除缓存
            redisTemplate.delete("user::"+u.getUsername());
        }

        u.setPassword(old.getPassword());
        User user=userService.update(u);
        if(user==null){
            return new ResultUtil<Object>().setErrorMsg("修改失败");
        }
        //删除该用户角色
        userRoleService.deleteByUserId(u.getId());
        if(roles!=null&&roles.length>0){
            //新角色
            for(String roleId : roles){
                UserRole ur = new UserRole();
                ur.setRoleId(roleId);
                ur.setUserId(u.getId());
                userRoleService.save(ur);
            }
        }
        //手动删除缓存
        redisTemplate.delete("userRole::"+u.getId());
        return new ResultUtil<Object>().setSuccessMsg("修改成功");
    }

    @RequestMapping(value = "/admin/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户")
    public Result<Object> add(@ModelAttribute User u,
                                 @RequestParam(required = false) String[] roles){

        if(StrUtil.isBlank(u.getUsername()) || StrUtil.isBlank(u.getPassword())){
            return new ResultUtil<>().setErrorMsg("缺少必需表单字段");
        }

        if(userService.findByUsername(u.getUsername())!=null){
            return new ResultUtil<>().setErrorMsg("该用户名已被注册");
        }
        //删除缓存
        redisTemplate.delete("user::"+u.getUsername());

        String encryptPass = new BCryptPasswordEncoder().encode(u.getPassword());
        u.setPassword(encryptPass);
        User user=userService.save(u);
        if(user==null){
            return new ResultUtil<>().setErrorMsg("添加失败");
        }
        if(roles!=null&&roles.length>0){
            //添加角色
            for(String roleId : roles){
                UserRole ur = new UserRole();
                ur.setUserId(u.getId());
                ur.setRoleId(roleId);
                userRoleService.save(ur);
            }
        }

        return new ResultUtil<>().setData(user);
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "获取当前登录用户接口")
    public Result<User> getUserInfo(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userService.findByUsername(user.getUsername());
        // 清除持久上下文环境 避免后面语句导致持久化
        entityManager.clear();
//        u.setPassword(null);
        return new ResultUtil<User>().setData(u);
    }
}
