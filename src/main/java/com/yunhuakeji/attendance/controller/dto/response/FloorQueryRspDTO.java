package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 楼层查询结果
 */
public class FloorQueryRspDTO {

    @ApiParam(name = "楼层ID")
    private String floorId;
    @ApiParam(name = "楼层名称")
    private String floorName;
}
