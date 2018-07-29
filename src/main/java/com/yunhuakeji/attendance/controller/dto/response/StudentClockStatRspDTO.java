package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 学生打卡统计
 */
public class StudentClockStatRspDTO {

    @ApiParam(name = "累计晚归")
    private int totalStayOutLate;

    @ApiParam(name = "累计到勤")
    private int totalClock;

    @ApiParam(name = "累计未归")
    private int totalStayOut;
}
