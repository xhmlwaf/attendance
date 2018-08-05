package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "CLOCK_SETTING")
public class ClockSetting implements Serializable {
    @Column(name = "CLOCK_START_TIME")
    private Long clockStartTime;

    @Column(name = "CLOCK_END_TIME")
    private Long clockEndTime;

    @Column(name = "CHECK_DORM_START_TIME")
    private Long checkDormStartTime;

    @Column(name = "CHECK_DORM_END_TIEM")
    private Long checkDormEndTiem;

    @Column(name = "DEVICE_CHECK")
    private Short deviceCheck;

    @Column(name = "CAROUSEL_TEXT")
    private String carouselText;

    private static final long serialVersionUID = 1L;

    /**
     * @return CLOCK_START_TIME
     */
    public Long getClockStartTime() {
        return clockStartTime;
    }

    /**
     * @param clockStartTime
     */
    public void setClockStartTime(Long clockStartTime) {
        this.clockStartTime = clockStartTime;
    }

    /**
     * @return CLOCK_END_TIME
     */
    public Long getClockEndTime() {
        return clockEndTime;
    }

    /**
     * @param clockEndTime
     */
    public void setClockEndTime(Long clockEndTime) {
        this.clockEndTime = clockEndTime;
    }

    /**
     * @return CHECK_DORM_START_TIME
     */
    public Long getCheckDormStartTime() {
        return checkDormStartTime;
    }

    /**
     * @param checkDormStartTime
     */
    public void setCheckDormStartTime(Long checkDormStartTime) {
        this.checkDormStartTime = checkDormStartTime;
    }

    /**
     * @return CHECK_DORM_END_TIEM
     */
    public Long getCheckDormEndTiem() {
        return checkDormEndTiem;
    }

    /**
     * @param checkDormEndTiem
     */
    public void setCheckDormEndTiem(Long checkDormEndTiem) {
        this.checkDormEndTiem = checkDormEndTiem;
    }

    /**
     * @return DEVICE_CHECK
     */
    public Short getDeviceCheck() {
        return deviceCheck;
    }

    /**
     * @param deviceCheck
     */
    public void setDeviceCheck(Short deviceCheck) {
        this.deviceCheck = deviceCheck;
    }

    public String getCarouselText() {
        return carouselText;
    }

    public void setCarouselText(String carouselText) {
        this.carouselText = carouselText;
    }
}