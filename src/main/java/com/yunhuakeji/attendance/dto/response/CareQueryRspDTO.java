package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CareQueryRspDTO {

    @ApiModelProperty(name = "关怀ID")
    private String careId;
    @ApiModelProperty(name = "学生ID")
    private String studentId;
    @ApiModelProperty(name = "学生姓名")
    private String studentName;
    @ApiModelProperty(name = "头像")
    private String profilePhoto;
    @ApiModelProperty(name = "学生学号")
    private String studentNo;
    @ApiModelProperty(name = "班级名称")
    private String className;
    @ApiModelProperty(name = "任务时间")
    private Date taskDate;

}
