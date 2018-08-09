package com.yunhuakeji.attendance.dto.response;

import java.util.Map;

import io.swagger.annotations.ApiParam;

public class ClockStatByBuildingRspDTO {

    @ApiParam(name = "宿舍楼ID")
    private String dormitoryId;
    @ApiParam(name = "宿舍楼名称")
    private String dormitoryName;
    @ApiParam(name = "统计结果 key：状态 value：数量")
    private Map<Integer,Integer> statMap;
}