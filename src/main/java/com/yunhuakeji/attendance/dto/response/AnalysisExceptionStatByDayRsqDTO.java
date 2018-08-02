package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 按日异常统计
 */
public class AnalysisExceptionStatByDayRsqDTO {

    @ApiParam(name = "昨日未归人数")
    private int lastNightStayoutNum;
    @ApiParam(name = "昨日晚归人数")
    private int lastNightStayoutLateNum;
}
