package com.yunhuakeji.attendance.dto.response;

import com.sun.tools.javac.util.List;
import io.swagger.annotations.ApiModelProperty;

/**
 * 按日异常统计
 */
public class AnalysisExceptionStatByDayOfWeekRsqDTO {

    @ApiModelProperty(name = "一周每日数据统计")
    private List<AnalysisDayExceptionDTO> dayOfWeekStatList;
}
