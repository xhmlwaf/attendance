package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.service.baseservice.MajorInfoService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class MajorCacheService extends DataCacheService {

  @Autowired
  private MajorInfoService majorInfoService;

  @Override
  public List listAll() {
    return majorInfoService.listAll();
  }

  @Override
  public long getPeriod() {
    return 1000 * 60 * 60 * 24;
  }

  public Map<Long, MajorInfo> getMajorInfoMap() {
    List<MajorInfo> majorInfoList = list();
    if (!CollectionUtils.isEmpty(majorInfoList)) {
      return majorInfoList.stream()
          .collect(Collectors.toMap(MajorInfo::getMajorId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }


}
