package cn.ripple.serviceimpl;

import cn.ripple.dao.DepartmentDao;
import cn.ripple.entity.Department;
import cn.ripple.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户接口实现
 * @author Edwin
 */
@Slf4j
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public DepartmentDao getRepository() {
        return departmentDao;
    }

    @Override
    public List<Department> findByParentIdOrderBySortOrder(String parentId) {

        return departmentDao.findByParentIdOrderBySortOrder(parentId);
    }

    @Override
    public List<Department> findByParentIdAndStatusOrderBySortOrder(String parentId, Integer status) {

        return departmentDao.findByParentIdAndStatusOrderBySortOrder(parentId, status);
    }
}
