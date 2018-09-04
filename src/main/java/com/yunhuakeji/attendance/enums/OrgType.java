package com.yunhuakeji.attendance.enums;

//    1-教学院系
//    2-科研机构
//    3-公共服务
//    4-党务部门
//    5-行政机构
//    6-附属单位
//    7-后勤部门
//    8-校办产业
//    9-其他
public enum OrgType {
  JXL((byte)1,"教学楼");

  private byte type;
  private String desc;

  OrgType(byte type, String desc) {
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
