package com.ripple.service;

import com.ripple.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 账号表
 * @author Edwin
 */
@Service("AccountServiceImpl")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountDAO accountDao;
}