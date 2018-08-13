package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 按日异常统计
 */
public class AnalysisExceptionStatByWeekRsqDTO {

    @ApiModelProperty(value = "本周未归人数")
    private int weekStayoutNum;
    @ApiModelProperty(value = "本周晚归人数")
    private int weekStayoutLateNum;
    @ApiModelProperty(value = "正常人数")
    private int weekNormalNum;
}
