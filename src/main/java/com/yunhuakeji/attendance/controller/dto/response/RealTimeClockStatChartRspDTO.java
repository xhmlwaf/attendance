package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

import java.util.Map;

public class RealTimeClockStatChartRspDTO {

    @ApiParam(name = "宿舍楼ID")
    private String dormitoryId;
    @ApiParam(name = "宿舍楼名称")
    private String dormitoryName;
    @ApiParam(name = "统计结果")
    private Map<Integer,Integer> statMap;
}
