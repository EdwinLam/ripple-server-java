package cn.ripple.dao.mapper;

import cn.ripple.entity.BasGroup;
import cn.ripple.result.group.StatisticalGroupMemberNumber;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Edwin
 * @Description:
 */
public interface BasGroupMapper extends BaseMapper<BasGroup> {

    StatisticalGroupMemberNumber statisticalGroupMemberNumber(@Param("groupId") Long groupId);
}
