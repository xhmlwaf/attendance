package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

/**
 * 学生信息
 */
public class StudentQueryRspDTO {

    @ApiParam(name = "学生ID")
    private String studentId;

    @ApiParam(name = "学生名称")
    private String studentName;

    @ApiParam(name = "学生学号")
    private String studentNo;

    @ApiParam(name = "头像")
    private String profilePhoto;

    @ApiParam(name = "学院ID")
    private String collegeId;

    @ApiParam(name = "学院名称")
    private String collegeName;

    @ApiParam(name = "专业ID")
    private String majorId;

    @ApiParam(name = "专业名称")
    private String majorName;

    @ApiParam(name = "班级ID")
    private String classId;

    @ApiParam(name = "班级名称")
    private String className;

    @ApiParam(name = "辅导员ID")
    private String instructorId;

    @ApiParam(name = "辅导员名称")
    private String instructorName;

    @ApiParam(name = "寝室号ID")
    private String dormitoryId;

    @ApiParam(name = "寝室名称")
    private String dormitoryName;

    @ApiParam(name = "床号")
    private String bedNo;


}
