package com.yunhuakeji.attendance.dao.bizdao.model;

public class ClockStatByStatusGenderDO {

  private byte clockStatus;

  private byte gender;

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

  public byte getGender() {
    return gender;
  }

  public void setGender(byte gender) {
    this.gender = gender;
  }
}
