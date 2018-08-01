package com.yunhuakeji.attendance.enums;

/**
 * 编制类型 1-专任教师 2-行政人员 3-教辅人员 4-工勤人员 5-校办企业职工 6-代课教师 7-兼任（职）教师 9-其他
 */
public enum EstablishmentType {
  ZRJS(1, "专任教师 "),
  XZRY(2, "行政人员"),
  JFRY(3, "教辅人员"),
  GQRY(4, "工勤人员"),
  XBQYZG(5, "校办企业职工"),
  DKJS(6, "代课教师"),
  JRJS(7, "兼任（职）教师"),
  QT(9, "其他");

  private int type;
  private String msg;

  EstablishmentType(int type, String msg) {
    this.type = type;
    this.msg = msg;
  }

  public int getType() {
    return type;
  }

  public String getMsg() {
    return msg;
  }
}
