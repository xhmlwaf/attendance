package com.yunhuakeji.attendance.enums;

public enum DormTypeEnum {
    BOY(1, "男生宿舍"),
    GIRL(2, "女生宿舍");

    private int type;
    private String msg;

    DormTypeEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static DormTypeEnum get(int val) {
        for (DormTypeEnum c : DormTypeEnum.values()) {
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
