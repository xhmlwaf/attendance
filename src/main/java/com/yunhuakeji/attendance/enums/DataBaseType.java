package com.yunhuakeji.attendance.enums;

public enum DataBaseType {

  DEFAULT("默认数据源"), BASEDATA("基础数据源");

  DataBaseType(String type) {
    this.type = type;
  }

  private String type;

  public String getType() {
    return type;
  }

}
