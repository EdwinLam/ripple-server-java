package com.ripple.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Edwin
 */
@Service("MallPackageServiceImpl")
public class MallPackageServiceImpl implements IMallPackageService {
    @Autowired
    private MallPackageDAO mallPackageDao;
}