package com.yunhuakeji.attendance.dto.response;

import java.math.BigDecimal;

import io.swagger.annotations.ApiParam;

public class AddressRspDTO {

    @ApiParam(name = "地址ID", required = true)
    private String id;

    @ApiParam(name = "地址名称", required = true)
    private String name;

    @ApiParam(name = "经度", required = true)
    private BigDecimal posLongitude;//经度

    @ApiParam(name = "纬度", required = true)
    private BigDecimal posLatitude;//

    @ApiParam(name = "范围", required = true)
    private Integer scope;

}
