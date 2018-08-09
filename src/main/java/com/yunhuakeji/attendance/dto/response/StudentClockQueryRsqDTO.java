package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiParam;

public class StudentClockQueryRsqDTO {

    @ApiParam(name = "年")
    private int year;
    @ApiParam(name = "月")
    private int month;
    @ApiParam(name = "日")
    private int day;
    @ApiParam(name = "最后更新时间")
    @JsonFormat(pattern = "yyyy年M月d日 HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateTime;
    @ApiParam(name = "状态")
    private Byte clockStatus;
    @ApiParam(name = "操作应用名")
    private String operateAppName;
    @ApiParam(name = "操作人ID")
    private Long operatorId;
    @ApiParam(name = "操作人名称")
    private String operatorName;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Byte getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(Byte clockStatus) {
        this.clockStatus = clockStatus;
    }

    public String getOperateAppName() {
        return operateAppName;
    }

    public void setOperateAppName(String operateAppName) {
        this.operateAppName = operateAppName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
