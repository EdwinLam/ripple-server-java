package cn.ripple.enums;

/**
 * Created by tengen on 2015/12/23.
 */
public enum AppCodeEnum {

    RIPPLE("RIPPLE", "RIPPLE");

    /**
     * 由一个值和名称实例化对象
     *
     * @param value
     * @param name
     */
    private AppCodeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String value;
    private final String name;

    /**
     * 取得枚举的值
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * 取得枚举的名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 根据枚举值获取对应的名称
     *
     * @param value
     * @return
     */
    public static String getNameByValue(String value) {
        AppCodeEnum[] types = AppCodeEnum.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getValue().equals(value))
                return types[i].getName();
        }
        return "";
    }
}
