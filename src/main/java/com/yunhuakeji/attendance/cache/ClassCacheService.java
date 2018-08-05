package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassCacheService extends DataCacheService{

    @Autowired
    private ClassInfoService classInfoService;

    @Override
    public List listAll() {
        return classInfoService.listAll();
    }

    @Override
    public long getPeriod() {
        return 0;
    }

}
