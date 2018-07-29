package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

import java.util.Date;

/**
 * 打卡历史查询
 */
public class StudentClockHistoryQueryRspDTO {

    @ApiParam(name = "操作时间")
    private Date operateTime;
    @ApiParam(name = "应用名称")
    private String appName;
    @ApiParam(name = "操作人ID")
    private String operator;
    @ApiParam(name = "操作人名称")
    private String operatorName;
    @ApiParam(name = "状态")
    private int clockStatus;
}
