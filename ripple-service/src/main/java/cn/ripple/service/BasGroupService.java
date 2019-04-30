package cn.ripple.service;

import cn.ripple.entity.BasGroup;
import cn.ripple.result.group.StatisticalGroupMemberNumber;

/**
 * @Author Edwin
 * @Description: 用户基本信息接口
 */
public interface BasGroupService extends BaseService<BasGroup, Long> {
    /**
     * 统计各种类型成员数量
     * @param groupId
     * @return
     */
    StatisticalGroupMemberNumber statisticalGroupMemberNumber(Long groupId);

}
