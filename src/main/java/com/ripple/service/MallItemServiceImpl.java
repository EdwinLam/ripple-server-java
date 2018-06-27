package com.ripple.service;

import com.ripple.dao.MallItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Edwin
 */
@Service("MallItemServiceImpl")
public class MallItemServiceImpl implements IMallItemService {
    @Autowired
    private MallItemDAO mallItemDao;
}