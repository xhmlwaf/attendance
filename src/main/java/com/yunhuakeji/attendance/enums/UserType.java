package com.yunhuakeji.attendance.enums;

/**
 * 用户类型 1-学生 2-教职工 3-其他
 */
public enum UserType {
  STUDENT(1, "学生"),
  STAFF(2, "教职工"),
  OTHER(3, "其他");


  private int type;
  private String msg;

  UserType(int type, String msg) {
    this.type = type;
    this.msg = msg;
  }

  public static UserType get(int val) {
    for (UserType c : UserType.values()) {
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
