package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 查寝日统计
 */
public class DormitoryCheckDayStatRspDTO {

    @ApiParam(name = "晚归人数")
    private int stayOutLateNum;

    @ApiParam(name = "到勤人数")
    private int clockNum;

    @ApiParam(name = "未归人数")
    private int stayOutNum;

    @ApiParam(name = "总人数")
    private int totalNum;
}
