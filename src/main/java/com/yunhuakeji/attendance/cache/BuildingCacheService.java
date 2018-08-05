package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.baseservice.BuildingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return 0;
    }
}
