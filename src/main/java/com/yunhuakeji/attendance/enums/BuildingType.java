package com.yunhuakeji.attendance.enums;

/**
 * 类别 1-教学楼 2-宿舍楼 3-商务楼（食堂、超市） 4-其他（图书馆、体育场）
 */
public enum BuildingType {
  JXL((byte) 1, "教学楼"),
  SSL((byte) 2, "宿舍楼"),
  SWL((byte) 3, "商务楼（食堂、超市）"),
  QT((byte) 4, "其他（图书馆、体育场）");

  private byte type;
  private String desc;

  BuildingType(byte type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  public static BuildingType get(int val) {
    for (BuildingType c : BuildingType.values()) {
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
