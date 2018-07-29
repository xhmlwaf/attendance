package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 查寝查询列表数据
 */
public class DormCheckQueryRspDTO {

    @ApiParam(name = "宿舍ID")
    private String dormId;

    @ApiParam(name = "宿舍名称")
    private String dormName;

    @ApiParam(name = "宿舍楼名称")
    private String DormitoryName;

    @ApiParam(name = "学生总数")
    private int studentNum;

    @ApiParam(name = "未归总数")
    private int stayoutNum;

    @ApiParam(name = "晚归总数")
    private int stayoutLateNum;

    @ApiParam(name = "宿舍类型")
    private int dromType;

}
