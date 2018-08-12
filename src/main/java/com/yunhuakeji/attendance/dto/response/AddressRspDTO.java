package com.yunhuakeji.attendance.dto.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

public class AddressRspDTO {

    @ApiModelProperty(name = "地址ID", required = true)
    private String id;

    @ApiModelProperty(name = "地址名称", required = true)
    private String name;

    @ApiModelProperty(name = "经度", required = true)
    private BigDecimal posLongitude;//经度

    @ApiModelProperty(name = "纬度", required = true)
    private BigDecimal posLatitude;//

    @ApiModelProperty(name = "范围", required = true)
    private Integer scope;

}
