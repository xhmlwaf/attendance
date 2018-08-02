package com.yunhuakeji.attendance.enums;

/**
 * 考勤状态枚举
 */
public enum ClockStatus {
    NOT_CLOCK(1, "未打卡"),
    CLOCK(2, "到勤"),
    STAYOUT_LATE(3, "晚归"),
    STAYOUT(4, "未归");

    private int type;
    private String msg;

    ClockStatus(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static ClockStatus get(int val) {
        for (ClockStatus c : ClockStatus.values()) {
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
