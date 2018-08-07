package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class DormitoryCheckWeekStatRspDTO {

    @ApiParam(name = "晚归人数")
    private int stayOutLateNum;

    @ApiParam(name = "未归人数")
    private int stayOutNum;

    @ApiParam(name = "总人数")
    private int totalNum;
}
