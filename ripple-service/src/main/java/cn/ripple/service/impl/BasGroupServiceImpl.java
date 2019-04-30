package cn.ripple.service.impl;

import cn.ripple.dao.BasGroupDao;
import cn.ripple.dao.mapper.BasGroupMapper;
import cn.ripple.entity.BasGroup;
import cn.ripple.result.group.StatisticalGroupMemberNumber;
import cn.ripple.service.BasGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Edwin
 * @Description: 用户基本信息接口实现
 */
@Slf4j
@Service
@Transactional
public class BasGroupServiceImpl implements BasGroupService {

    @Autowired
    private BasGroupDao basGroupDao;

    @Autowired
    private BasGroupMapper basGroupMapper;

    @Override
    public BasGroupDao getRepository() {
        return basGroupDao;
    }

    @Override
    public StatisticalGroupMemberNumber statisticalGroupMemberNumber(Long groupId) {
        BasGroup basGroup = get(groupId);
        StatisticalGroupMemberNumber statisticalGroupMemberNumber = basGroupMapper.statisticalGroupMemberNumber(groupId);
        statisticalGroupMemberNumber.setGroupId(basGroup.getId());
        statisticalGroupMemberNumber.setGroupName(basGroup.getGroupName());
        return statisticalGroupMemberNumber;
    }
}
