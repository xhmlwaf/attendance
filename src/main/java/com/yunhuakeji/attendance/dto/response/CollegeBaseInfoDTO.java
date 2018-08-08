package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

public class CollegeBaseInfoDTO {

    @ApiParam(name = "学院ID")
    private Long collegeId;
    @ApiParam(name = "学院名称")
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
