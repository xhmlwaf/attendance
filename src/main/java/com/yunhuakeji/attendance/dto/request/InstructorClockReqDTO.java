package com.yunhuakeji.attendance.dto.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 辅导员打卡
 */
public class InstructorClockReqDTO {

    @ApiModelProperty(value = "辅导员用户ID", required = true)
    @NotNull(message = "辅导员用户ID不能为空")
    private Long instructorId;

    @ApiModelProperty(value = "打卡二维码", required = true)
    @NotBlank(message = "打卡二维码不能为空")
    private String qrCode;

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
