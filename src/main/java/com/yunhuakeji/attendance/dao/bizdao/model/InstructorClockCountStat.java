package com.yunhuakeji.attendance.dao.bizdao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;


public class InstructorClockCountStat implements Serializable {

    private Long instructorId;

    private Long statCount;

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long getStatCount() {
        return statCount;
    }

    public void setStatCount(Long statCount) {
        this.statCount = statCount;
    }
}