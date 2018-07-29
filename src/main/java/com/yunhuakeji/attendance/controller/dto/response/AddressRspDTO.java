package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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
