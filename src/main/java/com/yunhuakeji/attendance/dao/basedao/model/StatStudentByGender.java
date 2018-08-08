package com.yunhuakeji.attendance.dao.basedao.model;

public class StatStudentByGender {

  private Byte genderType;

  private int statCount;

  public Byte getGenderType() {
    return genderType;
  }

  public void setGenderType(Byte genderType) {
    this.genderType = genderType;
  }

  public int getStatCount() {
    return statCount;
  }

  public void setStatCount(int statCount) {
    this.statCount = statCount;
  }
}
