package com.ripple.entity;

import java.util.Date;

public class MallItem extends BaseEntity {
    //唯一id
    private Integer id;

    //所属类型id
    private Integer typeId;

    //物品名称
    private String itemName;

    //最小价格
    private Float minPrice;

    //最大价格
    private Float maxPrice;

    //展示图片地址
    private String imageUrl;

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
     * 获取所属类型id
     *
     * @return typeId - 所属类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置所属类型id
     *
     * @param typeId 所属类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取物品名称
     *
     * @return itemName - 物品名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置物品名称
     *
     * @param itemName 物品名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * 获取最小价格
     *
     * @return minPrice - 最小价格
     */
    public Float getMinPrice() {
        return minPrice;
    }

    /**
     * 设置最小价格
     *
     * @param minPrice 最小价格
     */
    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 获取最大价格
     *
     * @return maxPrice - 最大价格
     */
    public Float getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置最大价格
     *
     * @param maxPrice 最大价格
     */
    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * 获取展示图片地址
     *
     * @return imageUrl - 展示图片地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置展示图片地址
     *
     * @param imageUrl 展示图片地址
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
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
        sb.append(", typeId=").append(typeId);
        sb.append(", itemName=").append(itemName);
        sb.append(", minPrice=").append(minPrice);
        sb.append(", maxPrice=").append(maxPrice);
        sb.append(", imageUrl=").append(imageUrl);
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
        MallItem other = (MallItem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
            && (this.getItemName() == null ? other.getItemName() == null : this.getItemName().equals(other.getItemName()))
            && (this.getMinPrice() == null ? other.getMinPrice() == null : this.getMinPrice().equals(other.getMinPrice()))
            && (this.getMaxPrice() == null ? other.getMaxPrice() == null : this.getMaxPrice().equals(other.getMaxPrice()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getCreateAt() == null ? other.getCreateAt() == null : this.getCreateAt().equals(other.getCreateAt()))
            && (this.getUpdateAt() == null ? other.getUpdateAt() == null : this.getUpdateAt().equals(other.getUpdateAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getItemName() == null) ? 0 : getItemName().hashCode());
        result = prime * result + ((getMinPrice() == null) ? 0 : getMinPrice().hashCode());
        result = prime * result + ((getMaxPrice() == null) ? 0 : getMaxPrice().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getCreateAt() == null) ? 0 : getCreateAt().hashCode());
        result = prime * result + ((getUpdateAt() == null) ? 0 : getUpdateAt().hashCode());
        return result;
    }
}