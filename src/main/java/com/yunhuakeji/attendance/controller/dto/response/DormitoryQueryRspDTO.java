package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 宿舍查询
 */
public class DormitoryQueryRspDTO {

    @ApiParam(name = "宿舍ID")
    private String dormitoryId;
    @ApiParam(name = "宿舍名称")
    private String dormitoryName;
}
