package com.yunhuakeji.attendance.enums;

/**
 * 关怀状态枚举
 */
public enum CareTypeEnum {
    NO(1, "未关怀"),
    YES(2, "已关怀");

    private int type;
    private String msg;

    CareTypeEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static CareTypeEnum get(int val) {
        for (CareTypeEnum c : CareTypeEnum.values()) {
            if (c.getType() == val) {
                return c;
            }
        }
        return null;
    }

    public int getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
