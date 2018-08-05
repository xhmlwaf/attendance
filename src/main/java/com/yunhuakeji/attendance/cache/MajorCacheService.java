package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.baseservice.MajorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorCacheService extends DataCacheService{

    @Autowired
    private MajorInfoService majorInfoService;

    @Override
    public List listAll() {
        return majorInfoService.listAll();
    }

    @Override
    public long getPeriod() {
        return 0;
    }

}
