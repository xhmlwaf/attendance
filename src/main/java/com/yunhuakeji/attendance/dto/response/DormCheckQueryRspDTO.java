package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查寝查询列表数据
 */
public class DormCheckQueryRspDTO {

    @ApiModelProperty(name = "宿舍ID")
    private String dormId;

    @ApiModelProperty(name = "宿舍名称")
    private String dormName;

    @ApiModelProperty(name = "宿舍楼名称")
    private String DormitoryName;

    @ApiModelProperty(name = "学生总数")
    private int studentNum;

    @ApiModelProperty(name = "未归总数")
    private int stayoutNum;

    @ApiModelProperty(name = "晚归总数")
    private int stayoutLateNum;

    @ApiModelProperty(name = "宿舍类型")
    private int dromType;

}
