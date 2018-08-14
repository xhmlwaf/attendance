package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 按日异常统计
 */
public class AnalysisExceptionStatByDayOfWeekRsqDTO {

    @ApiModelProperty(value = "一周每日数据统计")
    private List<AnalysisDayExceptionDTO> dayOfWeekStatList;

    public List<AnalysisDayExceptionDTO> getDayOfWeekStatList() {
        return dayOfWeekStatList;
    }

    public void setDayOfWeekStatList(List<AnalysisDayExceptionDTO> dayOfWeekStatList) {
        this.dayOfWeekStatList = dayOfWeekStatList;
    }
}
