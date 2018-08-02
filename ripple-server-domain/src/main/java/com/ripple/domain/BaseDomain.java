package com.ripple.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseDomain implements Serializable {
    //唯一id
    private Long id;

    //创建时间
    private Date createAt;

    //更新时间
    private Date updateAt;

    //创建用户uid
    private String createUid;

    //更新用户uid
    private String updateUid;

    //0-已删除 1-正常
    private Byte status;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateUid() {
        return createUid;
    }

    public void setCreateUid(String createUid) {
        this.createUid = createUid;
    }

    public String getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
