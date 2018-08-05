package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "CLOCK_DAY_SETTING")
public class ClockDaySetting implements Serializable {
    @Column(name = "YEAR_MONTH")
    private Integer yearMonth;

    @Column(name = "DAY")
    private Short day;

    private static final long serialVersionUID = 1L;

    public Integer getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Integer yearMonth) {
        this.yearMonth = yearMonth;
    }

    /**
     * @return DAY
     */
    public Short getDay() {
        return day;
    }

    /**
     * @param day
     */
    public void setDay(Short day) {
        this.day = day;
    }
}