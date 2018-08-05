package com.yunhuakeji.attendance.enums;

/**
 * 考勤状态枚举
 */
public enum ClockStatus {
    NOT_CLOCK((byte) 1, "未打卡"),
    CLOCK((byte) 2, "到勤"),
    STAYOUT_LATE((byte) 3, "晚归"),
    STAYOUT((byte) 4, "未归");

    private byte type;
    private String msg;

    ClockStatus(byte type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static ClockStatus get(byte val) {
        for (ClockStatus c : ClockStatus.values()) {
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
