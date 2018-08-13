package com.yunhuakeji.attendance.dto.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class AddressRspDTO {

    @ApiModelProperty(value = "地址ID", required = true)
    private String id;

    @ApiModelProperty(value = "地址名称", required = true)
    private String name;

    @ApiModelProperty(value = "经度", required = true)
    private BigDecimal posLongitude;//经度

    @ApiModelProperty(value = "纬度", required = true)
    private BigDecimal posLatitude;//

    @ApiModelProperty(value = "范围", required = true)
    private Integer scope;

}
