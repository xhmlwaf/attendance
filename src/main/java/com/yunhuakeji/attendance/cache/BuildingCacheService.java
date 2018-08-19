package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.enums.BuildingType;
import com.yunhuakeji.attendance.service.baseservice.BuildingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuildingCacheService extends DataCacheService {

    @Autowired
    private BuildingInfoService buildingInfoService;

    @Override
    public List listAll() {
        return buildingInfoService.listAll();
    }

    @Override
    public long getPeriod() {
        return 1000 * 60 * 60 * 24;
    }

    public Map<Long, BuildingInfo> getBuildingInfoMap() {
        Map<Long, BuildingInfo> buildingInfoMap = new HashMap<>();
        List<BuildingInfo> buildingInfoList = list();
        if (!CollectionUtils.isEmpty(buildingInfoList)) {
            for (BuildingInfo buildingInfo : buildingInfoList) {
                buildingInfoMap.put(buildingInfo.getBuildingId(), buildingInfo);

            }
        }
        return buildingInfoMap;
    }
}
