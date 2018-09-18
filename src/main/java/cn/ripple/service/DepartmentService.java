package cn.ripple.service;

import cn.ripple.service.BaseService;
import cn.ripple.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户接口
 * @author Edwin
 */
public interface DepartmentService extends BaseService<Department,String> {
    /**
     * 通过父id获取 升序
     *
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过父id和状态获取
     *
     * @param parentId
     * @param status
     * @return
     */
    List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status);
}
