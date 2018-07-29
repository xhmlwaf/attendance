package com.yunhuakeji.attendance.enums;

/**
 * 考勤状态枚举
 */
public enum ClockStatusEnum {
    NOT_CLOCK(1, "未打卡"),
    CLOCK(2, "到勤"),
    STAYOUT_LATE(3, "晚归"),
    STAYOUT(4, "未归");

    private int type;
    private String msg;

    ClockStatusEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static ClockStatusEnum get(int val) {
        for (ClockStatusEnum c : ClockStatusEnum.values()) {
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
