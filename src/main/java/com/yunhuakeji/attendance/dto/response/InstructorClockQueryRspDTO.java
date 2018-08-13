package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class InstructorClockQueryRspDTO {

    @ApiModelProperty(value = "打卡时间")
    private Date clockTime;
}
