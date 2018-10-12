package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.BuildingInfo;
import com.yunhuakeji.attendance.service.baseservice.BuildingInfoService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    return 1000 * 60 * 60 * 24;
  }

  public Map<Long, BuildingInfo> getBuildingInfoMap() {
    List<BuildingInfo> buildingInfoList = list();
    if (!CollectionUtils.isEmpty(buildingInfoList)) {
      return buildingInfoList.stream().collect(Collectors.toMap(BuildingInfo::getBuildingId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }
}
