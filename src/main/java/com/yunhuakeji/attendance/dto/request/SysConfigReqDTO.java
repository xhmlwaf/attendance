package com.yunhuakeji.attendance.dto.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;

/**
 * 系统配置
 */
public class SysConfigReqDTO {

    @ApiParam(name = "打卡开始时间", required = true)
    @NotNull(message = "打卡开始时间不能为空")
    private Date clockStartTime;

    @ApiParam(name = "打卡结束时间", required = true)
    @NotNull(message = "打卡结束时间不能为空")
    private Date clockEndTime;

    @ApiParam(name = "查寝开始时间", required = true)
    @NotNull(message = "查寝开始时间不能为空")
    private Date checkClockStartTime;

    @ApiParam(name = "查寝结束时间", required = true)
    @NotNull(message = "查寝结束时间不能为空")
    private Date checkClockEndTime;

    @ApiParam(name = "年", required = true)
    @NotNull(message = "年不能为空")
    private Integer year;

    @ApiParam(name = "月", required = true)
    @NotNull(message = "月不能为空")
    private Integer month;

    @ApiParam(name = "日期列表", required = true)
    private List<Integer> dayList;

    @ApiParam(name = "地址列表", required = true)
    @Size(min = 1,max = 100,message = "地址个数在1-100")
    @NotNull(message = "地址列表不能为空")
    private List<AddressReqDTO> addressReqDTOList;

    @ApiParam(name = "是否校验设备", required = true)
    @NotNull(message = "是否校验设备不能为空")
    private Byte checkDevice;
}

