package com.yunhuakeji.attendance.dao.bizdao.model;

public class UserClockCountStatDO {

  private byte clockStatus;

  private int statCount;

  private long userId;

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

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
