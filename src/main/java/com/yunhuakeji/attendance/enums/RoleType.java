package com.yunhuakeji.attendance.enums;

public enum RoleType {
    SecondaryCollegeAdmin((byte)1, "二级院校管理员"),
    DormitoryAdmin((byte)2, "宿舍管理员"),
    StudentsAffairsAdmin((byte)3, "学生处管理员");


    private byte type;
    private String msg;

    RoleType(byte type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static RoleType get(byte val) {
        for (RoleType c : RoleType.values()) {
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
