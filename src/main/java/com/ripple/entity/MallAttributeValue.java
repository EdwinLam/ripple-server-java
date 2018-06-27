package com.ripple.entity;

import java.util.Date;

public class MallAttributeValue extends BaseEntity {
    private Integer id;

    //物品id
    private Integer itemId;

    private Integer attributeId;

    private String value;

    //是否销售属性 0-不是 1-是
    private Integer isSale;

    private Date createAt;

    private Date updateAt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取物品id
     *
     * @return itemId - 物品id
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * 设置物品id
     *
     * @param itemId 物品id
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * @return attributeId
     */
    public Integer getAttributeId() {
        return attributeId;
    }

    /**
     * @param attributeId
     */
    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    /**
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    /**
     * 获取是否销售属性 0-不是 1-是
     *
     * @return isSale - 是否销售属性 0-不是 1-是
     */
    public Integer getIsSale() {
        return isSale;
    }

    /**
     * 设置是否销售属性 0-不是 1-是
     *
     * @param isSale 是否销售属性 0-不是 1-是
     */
    public void setIsSale(Integer isSale) {
        this.isSale = isSale;
    }

    /**
     * @return createAt
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * @param createAt
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * @return updateAt
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * @param updateAt
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemId=").append(itemId);
        sb.append(", attributeId=").append(attributeId);
        sb.append(", value=").append(value);
        sb.append(", isSale=").append(isSale);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append("]");
        return sb.toString();
    }
}