package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.service.baseservice.CollegeInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构缓存
 */
@Service
public class OrgCacheService extends DataCacheService {

  @Autowired
  private CollegeInfoService collegeInfoService;

  @Override
  public List listAll() {
    return collegeInfoService.listAll();
  }

  @Override
  public long getPeriod() {
    return 1000 * 60 * 60 * 24;
  }

  public Map<Long, CollegeInfo> getCollegeInfoMap() {
    Map<Long, CollegeInfo> collegeInfoMap = new HashMap<>();
    List<CollegeInfo> collegeInfoList = list();
    if (!CollectionUtils.isEmpty(collegeInfoList)) {
      for (CollegeInfo collegeInfo : collegeInfoList) {
        collegeInfoMap.put(collegeInfo.getOrgId(), collegeInfo);
      }
    }
    return collegeInfoMap;
  }


}
