package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.baseservice.CollegeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构缓存
 */
@Service
public class OrgCacheService extends DataCacheService{

    @Autowired
    private CollegeInfoService collegeInfoService;

    @Override
    public List listAll() {
        return collegeInfoService.listAll();
    }

    @Override
    public long getPeriod() {
        return 0;
    }

}
