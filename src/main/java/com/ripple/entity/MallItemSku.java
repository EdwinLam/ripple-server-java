package com.ripple.entity;

import java.util.Date;

public class MallItemSku extends BaseEntity {
    //唯一id
    private Integer id;

    //物品id
    private Integer itemId;

    //销售属性集合
    private String attributeValues;

    //销售价格
    private Float price;

    //库存数量
    private Integer stockQuantity;

    private Date createAt;

    private Date updateAt;

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
     * 获取销售属性集合
     *
     * @return attributeValues - 销售属性集合
     */
    public String getAttributeValues() {
        return attributeValues;
    }

    /**
     * 设置销售属性集合
     *
     * @param attributeValues 销售属性集合
     */
    public void setAttributeValues(String attributeValues) {
        this.attributeValues = attributeValues == null ? null : attributeValues.trim();
    }

    /**
     * 获取销售价格
     *
     * @return price - 销售价格
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置销售价格
     *
     * @param price 销售价格
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取库存数量
     *
     * @return stockQuantity - 库存数量
     */
    public Integer getStockQuantity() {
        return stockQuantity;
    }

    /**
     * 设置库存数量
     *
     * @param stockQuantity 库存数量
     */
    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
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
        sb.append(", attributeValues=").append(attributeValues);
        sb.append(", price=").append(price);
        sb.append(", stockQuantity=").append(stockQuantity);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
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
        MallItemSku other = (MallItemSku) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getAttributeValues() == null ? other.getAttributeValues() == null : this.getAttributeValues().equals(other.getAttributeValues()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getStockQuantity() == null ? other.getStockQuantity() == null : this.getStockQuantity().equals(other.getStockQuantity()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getUpdateAt() == null ? other.getUpdateAt() == null : this.getUpdateAt().equals(other.getUpdateAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getAttributeValues() == null) ? 0 : getAttributeValues().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getStockQuantity() == null) ? 0 : getStockQuantity().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getUpdateAt() == null) ? 0 : getUpdateAt().hashCode());
        return result;
    }
}