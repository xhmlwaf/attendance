package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;

public class ScreenConfigReqDTO {

    @ApiParam(name = "屏幕显示文本", required = true)
    @NotBlank(message = "屏幕显示文本不能为空")
    @Size(max = 30, message = "屏幕显示文本不超过30个字符")
    private String showText;
}
