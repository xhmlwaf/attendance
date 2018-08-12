package com.yunhuakeji.attendance.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查寝请求
 */
public class DormCheckReqDTO {

    @ApiModelProperty(name = "操作人ID", required = true)
    @NotBlank(message = "操作人ID不能为空")
    private Long operatorId;

    @ApiModelProperty(name = "学生ID", required = true)
    @NotBlank(message = "学生ID不能为空")
    private Long studentId;

    @ApiModelProperty(name = "考勤状态 1未打卡，2到勤，3晚归，4未归", required = true)
    @NotNull(message = "考勤状态不能为空")
    private Byte clockStatus;

    @ApiModelProperty(name = "备注", required = true)
    @Size(max = 30, message = "备注不超过30个字符")
    private String remark;

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Byte getClockStatus() {
        return clockStatus;
    }

    public void setClockStatus(Byte clockStatus) {
        this.clockStatus = clockStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
