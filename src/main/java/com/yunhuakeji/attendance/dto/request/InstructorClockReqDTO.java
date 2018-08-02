package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiParam;

/**
 * 辅导员打卡
 */
public class InstructorClockReqDTO {

    @ApiParam(name = "辅导员用户ID", required = true)
    @NotBlank(message = "辅导员用户ID不能为空")
    private String userId;

    @ApiParam(name = "打卡二维码", required = true)
    @NotBlank(message = "打卡二维码不能为空")
    private String qrCode;
}
