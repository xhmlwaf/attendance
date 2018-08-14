package com.yunhuakeji.attendance.enums;

/**
 * 关怀状态枚举
 */
public enum CareStatus {
    NO((byte)1, "未关怀"),
    YES((byte)2, "已关怀");

    private byte type;
    private String msg;

    CareStatus(byte type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static CareStatus get(byte val) {
        for (CareStatus c : CareStatus.values()) {
            if (c.getType() == val) {
                return c;
            }
        }
        return null;
    }

    public byte getType() {
        return type;
    }

    public String getMsg() {
        return msg;
    }
}
