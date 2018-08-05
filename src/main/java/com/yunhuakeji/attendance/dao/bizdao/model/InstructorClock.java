package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "INSTRUCTOR_CLOCK")
public class InstructorClock implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "INSTRUCTOR_ID")
    private Long instructorId;

    @Column(name = "CLOCK_TIME")
    private Date clockTime;

    @Column(name = "STAT_DATE")
    private Long statDate;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return INSTRUCTOR_ID
     */
    public Long getInstructorId() {
        return instructorId;
    }

    /**
     * @param instructorId
     */
    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    /**
     * @return CLOCK_TIME
     */
    public Date getClockTime() {
        return clockTime;
    }

    /**
     * @param clockTime
     */
    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }

    /**
     * @return STAT_DATE
     */
    public Long getStatDate() {
        return statDate;
    }

    /**
     * @param statDate
     */
    public void setStatDate(Long statDate) {
        this.statDate = statDate;
    }
}