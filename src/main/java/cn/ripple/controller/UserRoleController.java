package cn.ripple.controller;

import cn.ripple.controller.BaseController;
import cn.ripple.common.utils.PageUtil;
import cn.ripple.common.utils.ResultUtil;
import cn.ripple.common.vo.PageVo;
import cn.ripple.common.vo.Result;
import cn.ripple.entity.UserRole;
import cn.ripple.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


/**
 * @author Edwin
 */
@Slf4j
@RestController
@Api(description = "用户管理接口")
@RequestMapping("/ripple/userRole")
public class UserRoleController extends BaseController<UserRole, String>{

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public UserRoleService getService() {
        return userRoleService;
    }

}
