package com.yunhuakeji.attendance.enums;

/**
 * 10A-正常 10X-失效
 */
public enum State {
  NORMAL("10A", "正常"),
  INVALID("10X", "失效");

  private String state;
  private String desc;

  State(String state, String desc) {
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
