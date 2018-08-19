package com.yunhuakeji.attendance.dao.bizdao.model;

public class StudentClockStatusDO {

    private Long studentId;
    private Byte clockStatus;

    private int lxStayOut;
    private int lxStayOutLate;
    private int cared;


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

    public int getLxStayOut() {
        return lxStayOut;
    }

    public void setLxStayOut(int lxStayOut) {
        this.lxStayOut = lxStayOut;
    }

    public int getLxStayOutLate() {
        return lxStayOutLate;
    }

    public void setLxStayOutLate(int lxStayOutLate) {
        this.lxStayOutLate = lxStayOutLate;
    }

    public int getCared() {
        return cared;
    }

    public void setCared(int cared) {
        this.cared = cared;
    }
}
