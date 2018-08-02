package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiParam;

public class InstructorClockQueryRspDTO {

    @ApiParam(name = "打卡时间")
    private Date clockTime;
}
