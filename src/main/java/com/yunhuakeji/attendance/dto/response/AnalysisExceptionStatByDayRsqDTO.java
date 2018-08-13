package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 按日异常统计
 */
public class AnalysisExceptionStatByDayRsqDTO {

    @ApiModelProperty(value = "昨日未归人数")
    private int lastNightStayoutNum;
    @ApiModelProperty(value = "昨日晚归人数")
    private int lastNightStayoutLateNum;
}
