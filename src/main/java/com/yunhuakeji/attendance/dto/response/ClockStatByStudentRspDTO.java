package com.yunhuakeji.attendance.dto.response;

import java.util.Map;

import io.swagger.annotations.ApiParam;

public class ClockStatByStudentRspDTO extends StudentBaseInfoDTO{

    @ApiParam(name = "考勤状态")
    private Byte colckStatus;

}
