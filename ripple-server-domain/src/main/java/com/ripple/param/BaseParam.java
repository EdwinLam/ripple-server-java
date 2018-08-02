package com.ripple.param;

import java.io.Serializable;
import java.util.Date;

public class BaseParam implements Serializable {
    public static final String F_id = "id";

    public static final String F_createAt = "createAt";

    public static final String F_updateAt = "updateAt";

    public static final String F_createUid = "createUid";

    public static final String F_updateUid = "updateUid";

    public static final String F_status = "status";

    //唯一id
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
