package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * 二级学院管理员查询
 */
public class SecondaryCollegeAdminQueryRspDTO extends StaffBaseInfoDTO {


    List<CollegeBaseInfoDTO> collegeBaseInfoList;

    public List<CollegeBaseInfoDTO> getCollegeBaseInfoList() {
        return collegeBaseInfoList;
    }

    public void setCollegeBaseInfoList(List<CollegeBaseInfoDTO> collegeBaseInfoList) {
        this.collegeBaseInfoList = collegeBaseInfoList;
    }
}
