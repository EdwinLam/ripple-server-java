package com.ripple.entity;

import java.util.Date;

public class Node extends BaseEntity {
    //唯一id
    private Integer id;

    //是否模块 0-不是 1-是
    private Integer isModule;

    //所属应用id
    private Integer appId;

    //链路
    private String link;

    private Integer parentId;

    //节点标题
    private String title;

    //节点图标
    private String icon;

    //节点路径
    private String path;

    //节点名称
    private String name;

    //组件路径
    private String componentPath;

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
     * 获取是否模块 0-不是 1-是
     *
     * @return isModule - 是否模块 0-不是 1-是
     */
    public Integer getIsModule() {
        return isModule;
    }

    /**
     * 设置是否模块 0-不是 1-是
     *
     * @param isModule 是否模块 0-不是 1-是
     */
    public void setIsModule(Integer isModule) {
        this.isModule = isModule;
    }

    /**
     * 获取所属应用id
     *
     * @return appId - 所属应用id
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置所属应用id
     *
     * @param appId 所属应用id
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取链路
     *
     * @return link - 链路
     */
    public String getLink() {
        return link;
    }

    /**
     * 设置链路
     *
     * @param link 链路
     */
    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    /**
     * @return parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取节点标题
     *
     * @return title - 节点标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置节点标题
     *
     * @param title 节点标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取节点图标
     *
     * @return icon - 节点图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置节点图标
     *
     * @param icon 节点图标
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取节点路径
     *
     * @return path - 节点路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置节点路径
     *
     * @param path 节点路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取节点名称
     *
     * @return name - 节点名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置节点名称
     *
     * @param name 节点名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取组件路径
     *
     * @return componentPath - 组件路径
     */
    public String getComponentPath() {
        return componentPath;
    }

    /**
     * 设置组件路径
     *
     * @param componentPath 组件路径
     */
    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath == null ? null : componentPath.trim();
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
        sb.append(", isModule=").append(isModule);
        sb.append(", appId=").append(appId);
        sb.append(", link=").append(link);
        sb.append(", parentId=").append(parentId);
        sb.append(", title=").append(title);
        sb.append(", icon=").append(icon);
        sb.append(", path=").append(path);
        sb.append(", name=").append(name);
        sb.append(", componentPath=").append(componentPath);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Node other = (Node) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIsModule() == null ? other.getIsModule() == null : this.getIsModule().equals(other.getIsModule()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getLink() == null ? other.getLink() == null : this.getLink().equals(other.getLink()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getComponentPath() == null ? other.getComponentPath() == null : this.getComponentPath().equals(other.getComponentPath()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIsModule() == null) ? 0 : getIsModule().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getLink() == null) ? 0 : getLink().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getComponentPath() == null) ? 0 : getComponentPath().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }
}