package com.yunhuakeji.attendance.enums;

/**
 * 10A-正常 10X-失效
 */
public enum StateEnum {
  NORMAL("10A", "正常"),
  INVALID("10X", "失效");

  private String state;
  private String desc;

  StateEnum(String state, String desc) {
    this.state = state;
    this.desc = desc;
  }

  public String getState() {
    return state;
  }

  public String getDesc() {
    return desc;
  }
}
