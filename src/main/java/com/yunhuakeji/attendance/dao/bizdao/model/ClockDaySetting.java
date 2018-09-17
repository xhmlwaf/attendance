package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "CLOCK_DAY_SETTING")
public class ClockDaySetting implements Serializable {
    @Column(name = "YEAR_MONTH")
    private Integer yearMonth;

    @Column(name = "DAY")
    private Integer day;

    private static final long serialVersionUID = 1L;

    public Integer getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Integer yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClockDaySetting that = (ClockDaySetting) o;
        return Objects.equals(yearMonth, that.yearMonth) &&
            Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {

        return Objects.hash(yearMonth, day);
    }
}