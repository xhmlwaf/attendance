package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.RealTimeStatBiz;
import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.dto.response.ClockStatByBuildingRspDTO;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealTimeStatBizImpl implements RealTimeStatBiz {

    @Autowired
    private BuildingCacheService buildingCacheService;

    @Autowired
    private StudentClockService studentClockService;

    @Override
    public Result<List<ClockStatByBuildingRspDTO>> realTimeStatByBuilding() {

        List<BuildingInfo> buildingInfoList = buildingCacheService.list();

        return null;
    }
}
