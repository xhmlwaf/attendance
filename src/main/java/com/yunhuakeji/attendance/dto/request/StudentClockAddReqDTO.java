package com.yunhuakeji.attendance.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class StudentClockAddReqDTO {


    @ApiModelProperty(value = "学生ID", required = true)
    @NotNull(message = "学生ID不能为空")
    @Min(value = 1, message = "学生ID最小为1")
    private Long studentId;

    @ApiModelProperty(value = "设备ID", required = true)
    @NotBlank(message = "设备ID不能为空")
    @Size(max = 64, message = "设备ID最长64位")
    private String deviceId;

    @ApiModelProperty(value = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private BigDecimal posLongitude;//经度

    @ApiModelProperty(value = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private BigDecimal posLatitude;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public BigDecimal getPosLongitude() {
        return posLongitude;
    }

    public void setPosLongitude(BigDecimal posLongitude) {
        this.posLongitude = posLongitude;
    }

    public BigDecimal getPosLatitude() {
        return posLatitude;
    }

    public void setPosLatitude(BigDecimal posLatitude) {
        this.posLatitude = posLatitude;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
