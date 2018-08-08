package com.yunhuakeji.attendance.dto.response;

import java.util.List;

public class DormitoryAdminQueryRspDTO extends StaffBaseInfoDTO {

    List<BuildingBaseInfoDTO> buildingBaseInfoDTOList;

    public List<BuildingBaseInfoDTO> getBuildingBaseInfoDTOList() {
        return buildingBaseInfoDTOList;
    }

    public void setBuildingBaseInfoDTOList(List<BuildingBaseInfoDTO> buildingBaseInfoDTOList) {
        this.buildingBaseInfoDTOList = buildingBaseInfoDTOList;
    }
}
