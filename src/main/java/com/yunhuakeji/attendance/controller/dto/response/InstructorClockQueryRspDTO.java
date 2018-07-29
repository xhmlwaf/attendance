package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

import java.util.Date;

public class InstructorClockQueryRspDTO {

    @ApiParam(name = "打卡时间")
    private Date clockTime;
}
