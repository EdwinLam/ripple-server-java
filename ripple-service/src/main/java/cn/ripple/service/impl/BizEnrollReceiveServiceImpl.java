package cn.ripple.service.impl;

import cn.ripple.dao.BizEnrollReceiveDao;
import cn.ripple.service.BizEnrollReceiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author Edwin
 * @Description: 报名已读表接口实现
 */
@Slf4j
@Service
@Transactional
public class BizEnrollReceiveServiceImpl implements BizEnrollReceiveService {

    @Autowired
    private BizEnrollReceiveDao bizEnrollReceiveDao;

    @Override
    public BizEnrollReceiveDao getRepository() {
        return bizEnrollReceiveDao;
    }
}
