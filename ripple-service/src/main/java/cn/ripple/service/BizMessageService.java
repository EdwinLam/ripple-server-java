package cn.ripple.service;

import cn.ripple.entity.BizMessage;
import cn.ripple.enums.SourceCodeEnum;

/**
 * @Author Edwin
 * @Description: 消息总表接口
 */
public interface BizMessageService extends BaseService<BizMessage,String> {
    /**
     * @param groupId 分组id
     * @param userId 用户id
     * @param sourceId 业务id
     * @param creatorName 创建者名称
     * @param sourceCodeEnum 业务code
     * @param messageTitle 信息标题
     * @param messageContent 信息内容
     * @return
     */
    boolean saveMessage(Long groupId, Long userId,Long sourceId,String creatorName,SourceCodeEnum sourceCodeEnum,String messageTitle,String messageContent);
}
