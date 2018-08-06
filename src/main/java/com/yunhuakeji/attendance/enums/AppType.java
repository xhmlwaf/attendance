package com.yunhuakeji.attendance.enums;

/**
 * 学生/辅导员/宿舍员/学生处操作端
 */
public enum AppType {

    FDY((byte) 1, "辅导员"),
    SSY((byte) 2, "宿舍员"),
    XSC((byte) 3, "学生处"),
    XS((byte) 4, "学生");

    private byte type;
    private String desc;

    AppType(byte type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static AppType get(int val) {
        for (AppType c : AppType.values()) {
            if (c.getType() == val) {
                return c;
            }
        }
        return null;
    }


    public byte getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
