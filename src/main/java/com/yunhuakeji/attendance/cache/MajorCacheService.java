package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.service.baseservice.MajorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return 1000*60*60;
    }

    public Map<Long, MajorInfo> getMajorInfoMap() {
        Map<Long, MajorInfo> majorInfoMap = new HashMap<>();
        List<MajorInfo> majorInfoList = list();
        if (!CollectionUtils.isEmpty(majorInfoList)) {
            for (MajorInfo majorInfo : majorInfoList) {
                majorInfoMap.put(majorInfo.getMajorId(), majorInfo);
            }
        }
        return majorInfoMap;
    }


}
