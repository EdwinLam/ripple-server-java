package com.ripple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品属性表
 * @author Edwin
 */
@Service("MallAttributeServiceImpl")
public class MallAttributeServiceImpl implements IMallAttributeService {
    @Autowired
    private MallAttributeDAO mallAttributeDao;
}