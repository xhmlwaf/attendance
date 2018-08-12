package com.yunhuakeji.attendance.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AddressReqDTO {

    @ApiModelProperty(name = "地址名称", required = true)
    @NotNull(message = "地址名称不能为空")
    private String name;

    @ApiModelProperty(name = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private BigDecimal posLongitude;//经度

    @ApiModelProperty(name = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private BigDecimal posLatitude;//

    @ApiModelProperty(name = "范围", required = true)
    @NotNull(message = "范围不能为空")
    private Integer scope;

}
