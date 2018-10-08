package cn.ripple.controller;

import cn.ripple.common.constant.CommonConstant;
import cn.ripple.common.utils.ResultUtil;
import cn.ripple.common.vo.Result;
import cn.ripple.entity.Department;
import cn.ripple.entity.User;
import cn.ripple.service.BaseService;
import cn.ripple.service.DepartmentService;
import cn.ripple.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


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

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/getByParentId/{parentId}",method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    @Cacheable(key = "#parentId")
    public List<Department> getByParentId(@PathVariable String parentId){

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
        return list;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @CacheEvict(key = "#department.parentId")
    public Result<Department> add(@ModelAttribute Department department){

        Department d = departmentService.save(department);
        // 如果不是添加的一级 判断设置上级为父节点标识
        if(!CommonConstant.PARENT_ID.equals(department.getParentId())){
            Department parent = departmentService.get(department.getParentId());
            if(parent.getIsParent()==null||!parent.getIsParent()){
                parent.setIsParent(true);
                departmentService.update(parent);
                // 更新上级节点的缓存
                redisTemplate.delete("department::" + parent.getParentId());
            }
        }
        return new ResultUtil<Department>().setData(d);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "编辑")
    public Result<Department> edit(@ModelAttribute Department department){

        Department d = departmentService.update(department);
        // 手动删除所有部门缓存
        Set<String> keys = redisTemplate.keys("department:" + "*");
        redisTemplate.delete(keys);
        // 删除所有用户缓存
        Set<String> keysUser = redisTemplate.keys("user:" + "*");
        redisTemplate.delete(keysUser);
        return new ResultUtil<Department>().setData(d);
    }

    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @ApiOperation(value = "批量通过id删除")
    public Result<Object> delByIds(@PathVariable String[] ids){

        for(String id:ids){
            List<User> list = userService.findByDepartmentId(id);
            if(list!=null&&list.size()>0){
                return new ResultUtil<Object>().setErrorMsg("删除失败，包含正被用户使用关联的部门");
            }
        }
        for(String id:ids){
            departmentService.delete(id);
        }
        // 手动删除所有部门缓存
        Set<String> keys = redisTemplate.keys("department:" + "*");
        redisTemplate.delete(keys);
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }

    @Override
    public BaseService<Department, String> getService() {
        return departmentService;
    }
}
