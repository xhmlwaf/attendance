package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 查寝学生信息
 */
public class DormQueryStudentRsqDTO {

    @ApiParam(name = "学生ID")
    private String studentId;

    @ApiParam(name = "学生名称")
    private String studentName;

    @ApiParam(name = "学生学号")
    private String studentNo;

    @ApiParam(name = "头像")
    private String profilePhoto;

    @ApiParam(name = "床号")
    private String bedNo;

    @ApiParam(name = "状态")
    private int clockStatus;
}
