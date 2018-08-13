package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CareQueryRspDTO {

    @ApiModelProperty(value = "关怀ID")
    private String careId;
    @ApiModelProperty(value = "学生ID")
    private String studentId;
    @ApiModelProperty(value = "学生姓名")
    private String studentName;
    @ApiModelProperty(value = "头像")
    private String profilePhoto;
    @ApiModelProperty(value = "学生学号")
    private String studentNo;
    @ApiModelProperty(value = "班级名称")
    private String className;
    @ApiModelProperty(value = "任务时间")
    private Date taskDate;

}
