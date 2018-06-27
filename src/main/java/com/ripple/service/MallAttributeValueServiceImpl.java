package com.ripple.service;

import com.ripple.dao.MallAttributeValueDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Edwin
 */
@Service("MallAttributeValueServiceImpl")
public class MallAttributeValueServiceImpl implements IMallAttributeValueService {
    @Autowired
    private MallAttributeValueDAO mallAttributeValueDao;
}