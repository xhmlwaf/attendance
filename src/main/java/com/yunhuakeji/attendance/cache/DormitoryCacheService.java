package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.baseservice.DormitoryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryCacheService extends DataCacheService{

    @Autowired
    private DormitoryInfoService dormitoryInfoService;

    @Override
    public List listAll() {
        return dormitoryInfoService.listAll();
    }

    @Override
    public long getPeriod() {
        return 0;
    }

}
