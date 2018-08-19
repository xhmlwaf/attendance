package com.yunhuakeji.attendance.dao.bizdao.model;

public class DateStatusCountStatDO {
    private long statDate;
    private byte clockStatus;
    private int statCount;

    public long getStatDate() {
        return statDate;
    }

    public void setStatDate(long statDate) {
        this.statDate = statDate;
    }

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
