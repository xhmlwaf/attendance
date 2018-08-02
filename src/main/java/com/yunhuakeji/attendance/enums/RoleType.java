package com.yunhuakeji.attendance.enums;

public enum RoleType {
    SecondaryCollegeAdmin(1, "二级院校管理员"),
    DormitoryAdmin(2, "宿舍管理员"),
    StudentsAffairsAdmin(3, "学生处管理员");


    private int type;
    private String msg;

    RoleType(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static RoleType get(int val) {
        for (RoleType c : RoleType.values()) {
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
