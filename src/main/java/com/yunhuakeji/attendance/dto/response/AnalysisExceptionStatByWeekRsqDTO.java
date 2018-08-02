package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 按日异常统计
 */
public class AnalysisExceptionStatByWeekRsqDTO {

    @ApiParam(name = "本周未归人数")
    private int weekStayoutNum;
    @ApiParam(name = "本周晚归人数")
    private int weekStayoutLateNum;
    @ApiParam(name = "正常人数")
    private int weekNormalNum;
}
