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
}