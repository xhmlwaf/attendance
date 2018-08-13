package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查寝学生信息
 */
public class DormQueryStudentRsqDTO {

    @ApiModelProperty(value = "学生ID")
    private String studentId;

    @ApiModelProperty(value = "学生名称")
    private String studentName;

    @ApiModelProperty(value = "学生学号")
    private String studentNo;

    @ApiModelProperty(value = "头像")
    private String profilePhoto;

    @ApiModelProperty(value = "床号")
    private String bedNo;

    @ApiModelProperty(value = "状态")
    private int clockStatus;
}
