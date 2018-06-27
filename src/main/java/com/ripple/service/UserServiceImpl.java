package com.ripple.service;

import com.ripple.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Edwin
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDAO userDao;
}