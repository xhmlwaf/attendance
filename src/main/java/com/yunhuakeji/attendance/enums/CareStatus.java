package com.yunhuakeji.attendance.enums;

/**
 * 关怀状态枚举
 */
public enum CareStatus {
    NO(1, "未关怀"),
    YES(2, "已关怀");

    private int type;
    private String msg;

    CareStatus(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static CareStatus get(int val) {
        for (CareStatus c : CareStatus.values()) {
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
