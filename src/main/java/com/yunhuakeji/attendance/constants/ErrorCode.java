package com.yunhuakeji.attendance.constants;

/**
 * 系统错误码定义
 */
public enum ErrorCode {
    SUCCESS("000000", "success"),
    CLOCK_TIME_NOT_CONFIG("001000", "打卡时间未配置"),
    CHECK_TIME_NOT_CONFIG("001000", "查寝时间未配置"),
    CLOCK_ADDRESS_NOT_CONFIG("001000", "打卡地址未配置"),
    CLOCK_NOT_IN_CONFIG_AREA("001001", "未在打卡区域内"),
    PASSWORD_ERROR("001001", "密码错误");

    private String code;
    private String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ErrorCode get(String code) {
        for (ErrorCode e : ErrorCode.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
