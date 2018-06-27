package com.ripple.service;

import com.ripple.dao.AppDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Edwin
 */
@Service("AppServiceImpl")
public class AppServiceImpl implements IAppService {
    @Autowired
    private AppDAO appDao;
}