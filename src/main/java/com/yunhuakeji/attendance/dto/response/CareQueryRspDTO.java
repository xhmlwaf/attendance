package com.yunhuakeji.attendance.dto.response;

import java.util.Date;

import io.swagger.annotations.ApiParam;

public class CareQueryRspDTO {

    @ApiParam(name = "关怀ID")
    private String careId;
    @ApiParam(name = "学生ID")
    private String studentId;
    @ApiParam(name = "学生姓名")
    private String studentName;
    @ApiParam(name = "头像")
    private String profilePhoto;
    @ApiParam(name = "学生学号")
    private String studentNo;
    @ApiParam(name = "班级名称")
    private String className;
    @ApiParam(name = "任务时间")
    private Date taskDate;

}
