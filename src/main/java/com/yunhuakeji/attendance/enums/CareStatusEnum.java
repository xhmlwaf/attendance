package com.yunhuakeji.attendance.enums;

/**
 * 关怀状态枚举
 */
public enum CareStatusEnum {
    NO(1, "未关怀"),
    YES(2, "已关怀");

    private int type;
    private String msg;

    CareStatusEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static CareStatusEnum get(int val) {
        for (CareStatusEnum c : CareStatusEnum.values()) {
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
