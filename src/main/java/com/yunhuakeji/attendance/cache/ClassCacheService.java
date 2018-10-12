package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;
import com.yunhuakeji.attendance.util.ListUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ClassCacheService extends DataCacheService {

  @Autowired
  private ClassInfoService classInfoService;

  @Override
  public List listAll() {
    return classInfoService.listAll();
  }

  @Override
  public long getPeriod() {
    return 1000 * 60 * 60 * 24;
  }

  public Map<Long, ClassInfo> getClassInfoMap() {
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      return classInfoList.stream()
          .collect(Collectors.toMap(ClassInfo::getClassId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }


  public List<Long> getInstructorIds() {
    List<Long> instructorIds = new ArrayList<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      instructorIds = classInfoList.stream().map(ClassInfo::getInstructorId)
          .collect(Collectors.toList());
    }
    instructorIds = ListUtil.quChong(instructorIds);
    return instructorIds;
  }


  public List<Long> getClassIds() {
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      return classInfoList.stream().map(ClassInfo::getClassId).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public Map<Long, List<Long>> getInstructorClassMap() {
    Map<Long, List<Long>> classInstructorMap = new HashMap<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      for (ClassInfo classInfo : classInfoList) {
        List<Long> classIds = classInstructorMap.get(classInfo.getInstructorId());
        if (classIds == null) {
          classIds = new ArrayList<>();
        }
        classIds.add(classInfo.getClassId());
        classInstructorMap.put(classInfo.getInstructorId(), classIds);
      }
    }
    return classInstructorMap;
  }

  public Map<Long, ClassInfo> getInstructorClassInfoMap() {
    Map<Long, ClassInfo> classInstructorMap = new HashMap<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      for (ClassInfo classInfo : classInfoList) {
        classInstructorMap.put(classInfo.getInstructorId(), classInfo);
      }
    }
    return classInstructorMap;
  }

}
