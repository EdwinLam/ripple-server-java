package cn.ripple.utils;

import cn.ripple.entity.BaseEntity;
import cn.ripple.enums.DataStatusEnum;

/**
 * @Author: Edwin
 * @Description:
 */
public class EntityUtils {

    /**
     * 通过用户初始化实体
     * @param t
     * @param userId
     */
    public static void initEntityByUser(BaseEntity t, Long userId){
        t.setStatus(DataStatusEnum.ENABLED.getValue());
        t.setCreator(userId+"");
        t.setLastModifier(userId+"");
    }

}
