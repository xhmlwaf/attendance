package com.yunhuakeji.attendance.enums;

public enum RoleEnum {
    Instructor(1, "辅导员"),
    SecondaryCollegeAdmin(2, "二级院校管理员"),
    DormitoryAdmin(3, "宿舍管理员"),
    StudentsAffairsAdmin(4, "学生处管理员");


    private int type;
    private String msg;

    RoleEnum(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static RoleEnum get(int val) {
        for (RoleEnum c : RoleEnum.values()) {
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
