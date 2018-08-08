package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.service.baseservice.DormitoryInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DormitoryCacheService extends DataCacheService {

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

  public Map<Long, DormitoryInfo> getDormitoryMap() {
    List<DormitoryInfo> dormitoryInfoList = list();
    Map<Long, DormitoryInfo> dormitoryInfoMap = new HashMap<>();
    if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
      for (DormitoryInfo dormitoryInfo : dormitoryInfoList) {
        dormitoryInfoMap.put(dormitoryInfo.getDormitoryId(), dormitoryInfo);
      }
    }
    return dormitoryInfoMap;
  }

}
