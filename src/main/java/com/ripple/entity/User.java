package com.ripple.entity;

import java.util.Date;

public class User extends BaseEntity {
    //唯一id
    private Integer id;

    //账号id
    private Integer accountId;

    //用户名称
    private String userName;

    //0-男 1-女 2-其他
    private Integer sex;

    //是否主账号 0-不是 1-是 主账号不能解绑
    private Integer isMain;

    //联系号码
    private String phone;

    private Date createdAt;

    private Date updatedAt;

    /**
     * 获取唯一id
     *
     * @return id - 唯一id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置唯一id
     *
     * @param id 唯一id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取账号id
     *
     * @return accountId - 账号id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 设置账号id
     *
     * @param accountId 账号id
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * 获取用户名称
     *
     * @return userName - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取0-男 1-女 2-其他
     *
     * @return sex - 0-男 1-女 2-其他
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置0-男 1-女 2-其他
     *
     * @param sex 0-男 1-女 2-其他
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取是否主账号 0-不是 1-是 主账号不能解绑
     *
     * @return isMain - 是否主账号 0-不是 1-是 主账号不能解绑
     */
    public Integer getIsMain() {
        return isMain;
    }

    /**
     * 设置是否主账号 0-不是 1-是 主账号不能解绑
     *
     * @param isMain 是否主账号 0-不是 1-是 主账号不能解绑
     */
    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    /**
     * 获取联系号码
     *
     * @return phone - 联系号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系号码
     *
     * @param phone 联系号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountId=").append(accountId);
        sb.append(", userName=").append(userName);
        sb.append(", sex=").append(sex);
        sb.append(", isMain=").append(isMain);
        sb.append(", phone=").append(phone);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}