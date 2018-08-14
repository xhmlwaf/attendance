package com.yunhuakeji.attendance.enums;

/**
 * 1.晚归查寝 2平台后台
 */
public enum AppName {
  CQ((byte) 1, "晚归查寝"),
  HT((byte) 2, "平台后台");
  private byte type;
  private String desc;

  AppName(byte type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  public static AppName get(int val) {
    for (AppName c : AppName.values()) {
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
