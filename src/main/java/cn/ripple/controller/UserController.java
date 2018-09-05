package cn.ripple.controller;

import cn.ripple.common.utils.PageUtil;
import cn.ripple.common.utils.ResultUtil;
import cn.ripple.common.vo.PageVo;
import cn.ripple.common.vo.Result;
import cn.ripple.common.vo.SearchVo;
import cn.ripple.entity.User;
import cn.ripple.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Edwin
 */
@Slf4j
@RestController
@Api(description = "用户管理接口")
@RequestMapping("/ripple/user")
public class UserController extends BaseController<User, String>{

    @Autowired
    private UserService userService;

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
//            List<Role> list = iUserRoleService.findByUserId(u.getId());
//            u.setRoles(list);
            u.setPassword(null);
        }
        return new ResultUtil<Page<User>>().setData(page);
    }

}
