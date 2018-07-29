package com.yunhuakeji.attendance.controller.dto.response;

import com.yunhuakeji.attendance.controller.dto.request.AddressReqDTO;
import io.swagger.annotations.ApiParam;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 系统配置
 */
public class SysConfigRspDTO {

    @ApiParam(name = "打卡开始时间", required = true)
    private Date clockStartTime;

    @ApiParam(name = "打卡结束时间", required = true)
    private Date clockEndTime;

    @ApiParam(name = "查寝开始时间", required = true)
    private Date checkClockStartTime;

    @ApiParam(name = "查寝结束时间", required = true)
    private Date checkClockEndTime;

    @ApiParam(name = "年", required = true)
    private Integer year;

    @ApiParam(name = "月", required = true)
    private Integer month;

    @ApiParam(name = "日期列表", required = true)
    private List<Integer> dayList;

    @ApiParam(name = "地址列表", required = true)
    private List<AddressReqDTO> addressReqDTOList;

    @ApiParam(name = "是否校验设备", required = true)
    private Byte checkDevice;
}

