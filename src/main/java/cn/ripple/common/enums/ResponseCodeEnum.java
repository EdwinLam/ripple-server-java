package cn.ripple.common.enums;

/**
 * 服务器响应code
 */
public enum  ResponseCodeEnum {
    SUCCESS("1000", "成功"),
    BUSINESS_ERRO("4000",""),
    TOKEN_TIME_OUT("2000", "token超时"),
    ACCESS_DENY("2001", "没有权限访问"),
    CODE_901("901", "需要弹窗显示的错误");

    ResponseCodeEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private final String value;
    private final String name;

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getNameByValue(String value) {
        ResponseCodeEnum[] types = ResponseCodeEnum.values();
        for (int i = 0; i < types.length; i++) {
            if (types[i].getValue() == value)
                return types[i].getName();
        }
        return "";
    }
    }
