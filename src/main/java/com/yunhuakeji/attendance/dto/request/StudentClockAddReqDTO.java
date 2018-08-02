package com.yunhuakeji.attendance.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;

public class StudentClockAddReqDTO {


    @ApiParam(name = "学生ID", required = true)
    @NotNull(message = "学生ID不能为空")
    private String studentId;

    @ApiParam(name = "设备ID", required = true)
    @NotBlank(message = "设备ID不能为空")
    private String deviceId;

    @ApiParam(name = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private BigDecimal posLongitude;//经度

    @ApiParam(name = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private BigDecimal posLatitude;//

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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
