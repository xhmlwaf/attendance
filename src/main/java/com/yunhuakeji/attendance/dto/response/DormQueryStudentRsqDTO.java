package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查寝学生信息
 */
public class DormQueryStudentRsqDTO {

    @ApiModelProperty(name = "学生ID")
    private String studentId;

    @ApiModelProperty(name = "学生名称")
    private String studentName;

    @ApiModelProperty(name = "学生学号")
    private String studentNo;

    @ApiModelProperty(name = "头像")
    private String profilePhoto;

    @ApiModelProperty(name = "床号")
    private String bedNo;

    @ApiModelProperty(name = "状态")
    private int clockStatus;
}
