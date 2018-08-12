package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class ClockStatByStudentRspDTO extends StudentBaseInfoDTO{

    @ApiModelProperty(name = "考勤状态 1未打卡，2到勤，3晚归，4未归")
    private Byte colckStatus;

    public Byte getColckStatus() {
        return colckStatus;
    }

    public void setColckStatus(Byte colckStatus) {
        this.colckStatus = colckStatus;
    }
}
