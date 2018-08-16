package com.yunhuakeji.attendance.dao.bizdao.model;

public class StudentClockStatusCountStatDO {

  private long studentId;
  private int statCount;

  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  public int getStatCount() {
    return statCount;
  }

  public void setStatCount(int statCount) {
    this.statCount = statCount;
  }
}
