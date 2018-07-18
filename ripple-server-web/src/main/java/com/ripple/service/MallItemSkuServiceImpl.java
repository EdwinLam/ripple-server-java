package com.ripple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Edwin
 */
@Service("MallItemSkuServiceImpl")
public class MallItemSkuServiceImpl implements IMallItemSkuService {
    @Autowired
    private MallItemSkuDAO mallItemSkuDao;
}