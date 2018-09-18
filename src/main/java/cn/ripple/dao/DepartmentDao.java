package cn.ripple.dao;

import cn.ripple.dao.BaseDao;
import cn.ripple.entity.Department;

import java.util.List;

/**
 * 用户数据处理层
 * @author Edwin
 */
public interface DepartmentDao extends BaseDao<Department,String> {
    /**
     * 通过父id获取 升序
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过父id和状态获取 升序
     * @param parentId
     * @param status
     * @return
     */
    List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status);
}
