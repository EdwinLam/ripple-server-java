package com.ripple.entity;

public class MallPackage extends BaseEntity {
    //唯一id
    private Integer id;

    //套餐名称
    private String packageName;

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
     * 获取套餐名称
     *
     * @return packageName - 套餐名称
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 设置套餐名称
     *
     * @param packageName 套餐名称
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName == null ? null : packageName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", packageName=").append(packageName);
        sb.append("]");
        return sb.toString();
    }
}