package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

import java.util.Date;

public class StudentClockQueryRsqDTO {

    @ApiParam(name = "年")
    private int year;
    @ApiParam(name = "月")
    private int month;
    @ApiParam(name = "最后更新时间")
    private Date lastUpdateTime;
    @ApiParam(name = "状态")
    private int clockStatus;
}
