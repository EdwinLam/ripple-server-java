package cn.ripple.controller;

import cn.ripple.common.utils.ResultUtil;
import cn.ripple.common.vo.Result;
import cn.ripple.entity.Role;
import cn.ripple.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Edwin
 */
@Slf4j
@RestController
@Api(description = "角色权限管理接口")
@RequestMapping("/ripple/role")
public class RoleController extends BaseController<Role, String>{

    @Autowired
    private RoleService roleService;

    @Override
    public RoleService getService() {
        return roleService;
    }

    @RequestMapping(value = "/getAllList",method = RequestMethod.GET)
    @ApiOperation(value = "获取全部角色")
    public List<Role>  roleGetAll(){
        return roleService.getAll();
    }


}
