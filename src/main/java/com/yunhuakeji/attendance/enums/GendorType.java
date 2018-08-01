package com.yunhuakeji.attendance.enums;

/**
 * 性别 0-未知 1-男 2-女
 */
public enum GendorType {
  MALE((byte) 1, "男"),
  FEMALE((byte) 2, "女"),
  UNKONW((byte) 3, "未知 ");

  private byte type;
  private String desc;

  GendorType(byte type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  public byte getType() {
    return type;
  }

  public String getDesc() {
    return desc;
  }
}
