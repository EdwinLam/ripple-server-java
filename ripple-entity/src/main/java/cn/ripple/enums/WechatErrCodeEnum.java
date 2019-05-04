package cn.ripple.enums;

/**
 * @Author: Edwin
 * @Description:
 */
public enum WechatErrCodeEnum {
    SYSTEM_BUSY(-1, "系统繁忙"),
    SUCCESS(0, "请求成功"),
    CODE_INVALID(40029, "code无效"),
    API_LIMIT(45011,"频率限制，每个用户每分钟100次");

    WechatErrCodeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    private final int value;
    private final String name;




    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(int value) {
        WechatErrCodeEnum[] types = WechatErrCodeEnum.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getValue()==value)
                return types[i].getName();
        }
        return "";
    }
}
