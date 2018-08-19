package com.yunhuakeji.attendance.dao.bizdao.model;

public class StudentStatusCountDO {

    private Long studentId;
    private Byte clockStatus;
    private int statCount;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Byte getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(Byte clockStatus) {
        this.clockStatus = clockStatus;
    }

    public int getStatCount() {
        return statCount;
    }

    public void setStatCount(int statCount) {
        this.statCount = statCount;
    }
}
