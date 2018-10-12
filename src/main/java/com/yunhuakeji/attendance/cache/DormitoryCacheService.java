package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.service.baseservice.DormitoryInfoService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    return 1000 * 60 * 60 * 24;
  }

  public Map<Long, DormitoryInfo> getDormitoryMap() {
    List<DormitoryInfo> dormitoryInfoList = list();
    if (!CollectionUtils.isEmpty(dormitoryInfoList)) {
      return dormitoryInfoList.stream().collect(
          Collectors.toMap(DormitoryInfo::getDormitoryId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

}
