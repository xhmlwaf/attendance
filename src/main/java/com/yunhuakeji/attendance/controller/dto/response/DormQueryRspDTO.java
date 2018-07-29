package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 宿舍查询
 */
public class DormQueryRspDTO {

    @ApiParam(name = "宿舍ID")
    private String dormId;
    @ApiParam(name = "宿舍名称")
    private String dormName;
}
