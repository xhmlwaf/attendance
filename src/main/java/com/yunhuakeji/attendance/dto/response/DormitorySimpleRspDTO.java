package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

/**
 * 宿舍查询
 */
public class DormitorySimpleRspDTO {

    @ApiModelProperty(name = "宿舍ID")
    private Long dormitoryId;
    @ApiModelProperty(name = "宿舍名称")
    private String dormitoryName;

    public Long getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Long dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryName() {
        return dormitoryName;
    }

    public void setDormitoryName(String dormitoryName) {
        this.dormitoryName = dormitoryName;
    }
}
