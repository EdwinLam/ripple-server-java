package com.ripple.controller;

import com.ripple.dao.account.IAccountDAO;
import com.ripple.domain.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class AccountController {
    @Autowired
    private IAccountDAO accountDAO;

    @ResponseBody
    @RequestMapping(value = "/test")
    public Account queryStudyResume(long id) {
        return accountDAO.fetch(id);
    }
}