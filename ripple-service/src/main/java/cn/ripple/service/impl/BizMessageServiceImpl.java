package cn.ripple.service.impl;

import cn.ripple.dao.BizMessageDao;
import cn.ripple.entity.BizMessage;
import cn.ripple.enums.SourceCodeEnum;
import cn.ripple.service.BizMessageService;
import cn.ripple.utils.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Edwin
 * @Description: 消息总表接口实现
 */
@Slf4j
@Service
@Transactional
public class BizMessageServiceImpl implements BizMessageService {

    @Autowired
    private BizMessageDao bizMessageDao;

    @Override
    public BizMessageDao getRepository() {
        return bizMessageDao;
    }

    @Override
    @Transactional
    public boolean saveMessage(Long groupId, Long userId,Long sourceId,String creatorName,SourceCodeEnum sourceCodeEnum,String messageTitle,String messageContent){
        BizMessage bizMessage = new BizMessage();
        bizMessage.setMessageTitle(messageTitle);
        bizMessage.setMessageContent(messageContent);
        EntityUtils.initEntityByUser(bizMessage,userId);
        bizMessage.setGroupId(groupId);
        bizMessage.setCreatorName(creatorName);
        bizMessage.setSourceCode(sourceCodeEnum.getValue());
        bizMessage.setSourceId(sourceId);
        bizMessageDao.save(bizMessage);
        return true;
    }
}
