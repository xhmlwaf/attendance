package com.yunhuakeji.attendance.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DormitoryAdminQueryRspDTO extends StaffBaseInfoDTO {

    @ApiModelProperty(value = "管理的楼栋信息列表")
    List<BuildingBaseInfoDTO> buildingList;

    public List<BuildingBaseInfoDTO> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<BuildingBaseInfoDTO> buildingList) {
        this.buildingList = buildingList;
    }
}
