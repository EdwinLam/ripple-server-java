package com.ripple.param.account;

import com.ripple.param.BaseParam;

public class AccountParam extends BaseParam {
    public static final String F_accountName = "accountName";

    //账号名称
    private String accountName;

    /**
     * 获取账号名称
     *
     * @return accountName - 账号名称
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 设置账号名称
     *
     * @param accountName 账号名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }
}