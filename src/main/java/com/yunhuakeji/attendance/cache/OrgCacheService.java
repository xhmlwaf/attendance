package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.CollegeInfo;
import com.yunhuakeji.attendance.service.baseservice.CollegeInfoService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    List<CollegeInfo> collegeInfoList = list();
    if (!CollectionUtils.isEmpty(collegeInfoList)) {
      return collegeInfoList.stream()
          .collect(Collectors.toMap(CollegeInfo::getOrgId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }


}
