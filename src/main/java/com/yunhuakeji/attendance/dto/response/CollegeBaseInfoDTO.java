package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

public class CollegeBaseInfoDTO {

    @ApiModelProperty(name = "学院ID")
    private Long collegeId;
    @ApiModelProperty(name = "学院名称")
    private String collegeName;

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
}
