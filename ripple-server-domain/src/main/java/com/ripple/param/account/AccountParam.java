package com.ripple.param.account;

import com.ripple.param.BaseParam;

public class AccountParam extends BaseParam {
    public static final String F_id = "id";

    public static final String F_accountName = "accountName";

    public static final String F_createAt = "createAt";

    public static final String F_updateAt = "updateAt";

    public static final String F_createUid = "createUid";

    public static final String F_updateUid = "updateUid";

    public static final String F_status = "status";

    //唯一id
    private Long id;

    //账号名称
    private String accountName;

    //创建时间
    private java.util.Date createAt;

    //更新时间
    private java.util.Date updateAt;

    //创建用户uid
    private String createUid;

    //更新用户uid
    private String updateUid;

    //0-已删除 1-正常
    private Byte status;

    /**
     * 获取唯一id
     *
     * @return id - 唯一id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置唯一id
     *
     * @param id 唯一id
     */
    public void setId(Long id) {
        this.id = id;
    }

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

    /**
     * 获取创建时间
     *
     * @return createAt - 创建时间
     */
    public java.util.Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(java.util.Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取更新时间
     *
     * @return updateAt - 更新时间
     */
    public java.util.Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置更新时间
     *
     * @param updateAt 更新时间
     */
    public void setUpdateAt(java.util.Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * 获取创建用户uid
     *
     * @return createUid - 创建用户uid
     */
    public String getCreateUid() {
        return createUid;
    }

    /**
     * 设置创建用户uid
     *
     * @param createUid 创建用户uid
     */
    public void setCreateUid(String createUid) {
        this.createUid = createUid == null ? null : createUid.trim();
    }

    /**
     * 获取更新用户uid
     *
     * @return updateUid - 更新用户uid
     */
    public String getUpdateUid() {
        return updateUid;
    }

    /**
     * 设置更新用户uid
     *
     * @param updateUid 更新用户uid
     */
    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid == null ? null : updateUid.trim();
    }

    /**
     * 获取0-已删除 1-正常
     *
     * @return status - 0-已删除 1-正常
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0-已删除 1-正常
     *
     * @param status 0-已删除 1-正常
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}