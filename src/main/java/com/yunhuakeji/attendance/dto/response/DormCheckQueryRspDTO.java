package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查寝查询列表数据
 */
public class DormCheckQueryRspDTO {

    @ApiModelProperty(value = "宿舍ID")
    private String dormId;

    @ApiModelProperty(value = "宿舍名称")
    private String dormName;

    @ApiModelProperty(value = "宿舍楼名称")
    private String DormitoryName;

    @ApiModelProperty(value = "学生总数")
    private int studentNum;

    @ApiModelProperty(value = "未归总数")
    private int stayoutNum;

    @ApiModelProperty(value = "晚归总数")
    private int stayoutLateNum;

    @ApiModelProperty(value = "宿舍类型")
    private int dromType;

}
