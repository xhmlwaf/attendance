package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiParam;

public class StudentClockQueryRsqDTO {

    @ApiParam(name = "年")
    private int year;
    @ApiParam(name = "月")
    private int month;
    @ApiParam(name = "操作应用")
    private String app;
    @ApiParam(name = "最后更新时间")
    private Date lastUpdateTime;
    @ApiParam(name = "状态")
    private int clockStatus;
}
