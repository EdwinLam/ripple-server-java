package com.ripple.entity;

import java.util.Date;

public class Account extends BaseEntity {
    //唯一id
    private Integer id;

    //手机号码
    private String phone;

    //密码
    private String password;

    //email地址
    private String email;

    //微博id
    private String weiboId;

    //微信id
    private String weixinId;

    private Date createdAt;

    private Date updatedAt;

    //创建uid
    private String createUid;

    //更新uid
    private String updateUid;

    //0-正常 1-删除
    private Byte status;

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
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取email地址
     *
     * @return email - email地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email地址
     *
     * @param email email地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取微博id
     *
     * @return weiboId - 微博id
     */
    public String getWeiboId() {
        return weiboId;
    }

    /**
     * 设置微博id
     *
     * @param weiboId 微博id
     */
    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId == null ? null : weiboId.trim();
    }

    /**
     * 获取微信id
     *
     * @return weixinId - 微信id
     */
    public String getWeixinId() {
        return weixinId;
    }

    /**
     * 设置微信id
     *
     * @param weixinId 微信id
     */
    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId == null ? null : weixinId.trim();
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

    /**
     * 获取创建uid
     *
     * @return createUid - 创建uid
     */
    public String getCreateUid() {
        return createUid;
    }

    /**
     * 设置创建uid
     *
     * @param createUid 创建uid
     */
    public void setCreateUid(String createUid) {
        this.createUid = createUid == null ? null : createUid.trim();
    }

    /**
     * 获取更新uid
     *
     * @return updateUid - 更新uid
     */
    public String getUpdateUid() {
        return updateUid;
    }

    /**
     * 设置更新uid
     *
     * @param updateUid 更新uid
     */
    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid == null ? null : updateUid.trim();
    }

    /**
     * 获取0-正常 1-删除
     *
     * @return status - 0-正常 1-删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0-正常 1-删除
     *
     * @param status 0-正常 1-删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", weiboId=").append(weiboId);
        sb.append(", weixinId=").append(weixinId);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createUid=").append(createUid);
        sb.append(", updateUid=").append(updateUid);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}