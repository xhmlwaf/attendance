package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiParam;

public class CareUpdateReqDTO {

    @ApiParam(name = "关怀ID", required = true)
    @NotBlank(message = "关怀ID不能为空")
    private String careId;
    @ApiParam(name = "辅导员用户ID", required = true)
    @NotBlank(message = "辅导员用户ID不能为空")
    private String userId;
    @ApiParam(name = "备注", required = true)
    @NotBlank(message = "备注不能为空")
    private String remark;
}
