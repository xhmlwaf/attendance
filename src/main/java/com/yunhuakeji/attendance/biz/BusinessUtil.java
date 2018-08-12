package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class BusinessUtil {


    public static List<Long> getBuildingIds(List<BuildingInfo> buildingInfoList) {
        List buildingIds = new ArrayList();
        if (!CollectionUtils.isEmpty(buildingInfoList)) {

            for (BuildingInfo buildingInfo : buildingInfoList) {
                buildingIds.add(buildingInfo.getBuildingId());
            }
        }
        return buildingIds;
    }



}
