package com.yunhuakeji.attendance.controller.dto.request;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 查寝请求
 */
public class DormCheckReqDTO {

    @ApiParam(name = "操作人ID", required = true)
    @NotBlank(message = "操作人ID不能为空")
    private String operatorId;

    @ApiParam(name = "学生ID", required = true)
    @NotBlank(message = "学生ID不能为空")
    private String studentId;

    @ApiParam(name = "考勤状态", required = true)
    @NotNull(message = "考勤状态不能为空")
    private Integer clockStatus;

    @ApiParam(name = "备注", required = true)
    @Size(max = 30, message = "备注不超过30个字符")
    private String remark;
}
