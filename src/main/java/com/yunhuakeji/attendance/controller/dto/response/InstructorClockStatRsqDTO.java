package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 辅导员打卡统计
 */
public class InstructorClockStatRsqDTO {

    @ApiParam(name = "打卡次数统计")
    private int totalClockCount;
}
