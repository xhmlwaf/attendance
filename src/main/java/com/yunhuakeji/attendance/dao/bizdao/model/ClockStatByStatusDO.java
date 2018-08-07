package com.yunhuakeji.attendance.dao.bizdao.model;

public class ClockStatByStatusDO {

  private byte clockStatus;

  private int statCount;

  public byte getClockStatus() {
    return clockStatus;
  }

  public void setClockStatus(byte clockStatus) {
    this.clockStatus = clockStatus;
  }

  public int getStatCount() {
    return statCount;
  }

  public void setStatCount(int statCount) {
    this.statCount = statCount;
  }
}
