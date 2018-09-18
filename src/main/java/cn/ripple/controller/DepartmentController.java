package cn.ripple.controller;

import cn.ripple.common.constant.CommonConstant;
import cn.ripple.common.utils.ResultUtil;
import cn.ripple.common.vo.Result;
import cn.ripple.entity.Department;
import cn.ripple.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Edwin
 */
@Slf4j
@RestController
@Api(description = "部门管理接口")
@RequestMapping("/ripple/department")
@CacheConfig(cacheNames = "department")
@Transactional
public class DepartmentController extends BaseController<Department, String>{
    @Autowired
    private DepartmentService departmentService;

    @Override
    public DepartmentService getService() {
        return departmentService;
    }
    @RequestMapping(value = "/getByParentId/{parentId}",method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    @Cacheable(key = "#parentId")
    public Result<List<Department>> getByParentId(@PathVariable String parentId){

        List<Department> list = departmentService.findByParentIdOrderBySortOrder(parentId);
        // lambda表达式
        list.forEach(item -> {
            if(!CommonConstant.PARENT_ID.equals(item.getParentId())){
                Department parent = departmentService.get(item.getParentId());
                item.setParentTitle(parent.getTitle());
            }else{
                item.setParentTitle("一级部门");
            }
        });
        return new ResultUtil<List<Department>>().setData(list);
    }
}
