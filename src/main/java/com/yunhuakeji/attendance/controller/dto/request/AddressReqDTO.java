package com.yunhuakeji.attendance.controller.dto.request;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AddressReqDTO {

    @ApiParam(name = "地址名称", required = true)
    @NotNull(message = "地址名称不能为空")
    private String name;

    @ApiParam(name = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private BigDecimal posLongitude;//经度

    @ApiParam(name = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private BigDecimal posLatitude;//

    @ApiParam(name = "范围", required = true)
    @NotNull(message = "范围不能为空")
    private Integer scope;

}
