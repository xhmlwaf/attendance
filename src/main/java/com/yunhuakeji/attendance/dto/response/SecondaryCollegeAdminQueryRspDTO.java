package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 二级学院管理员查询
 */
public class SecondaryCollegeAdminQueryRspDTO extends StaffBaseInfoDTO {

    @ApiModelProperty(value = "管理的学院信息列表")
    List<CollegeBaseInfoDTO> collegeList;

    public List<CollegeBaseInfoDTO> getCollegeList() {
        return collegeList;
    }

    public void setCollegeList(List<CollegeBaseInfoDTO> collegeList) {
        this.collegeList = collegeList;
    }
}
