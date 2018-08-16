package com.yunhuakeji.attendance.dao.bizdao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "CLOCK_SETTING")
public class ClockSetting implements Serializable {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "CLOCK_START_TIME")
    private Long clockStartTime;

    @Column(name = "CLOCK_END_TIME")
    private Long clockEndTime;

    @Column(name = "CHECK_DORM_START_TIME")
    private Long checkDormStartTime;

    @Column(name = "CHECK_DORM_END_TIME")
    private Long checkDormEndTime;

    @Column(name = "DEVICE_CHECK")
    private Byte deviceCheck;

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
     * @return CHECK_DORM_END_TIME
     */
    public Long getCheckDormEndTime() {
        return checkDormEndTime;
    }

    /**
     * @param checkDormEndTime
     */
    public void setCheckDormEndTime(Long checkDormEndTime) {
        this.checkDormEndTime = checkDormEndTime;
    }

    /**
     * @return DEVICE_CHECK
     */
    public Byte getDeviceCheck() {
        return deviceCheck;
    }

    /**
     * @param deviceCheck
     */
    public void setDeviceCheck(Byte deviceCheck) {
        this.deviceCheck = deviceCheck;
    }

    public String getCarouselText() {
        return carouselText;
    }

    public void setCarouselText(String carouselText) {
        this.carouselText = carouselText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}