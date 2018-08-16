package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    return 1000 * 60 * 60;
  }

  public Map<Long, ClassInfo> getClassInfoMap() {
    Map<Long, ClassInfo> classInfoMap = new HashMap<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      for (ClassInfo classInfo : classInfoList) {
        classInfoMap.put(classInfo.getClassId(), classInfo);
      }
    }
    return classInfoMap;
  }

  public List<Long> getInstructorIds() {
    List<Long> instructorIds = new ArrayList<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      for (ClassInfo classInfo : classInfoList) {
        instructorIds.add(classInfo.getInstructorId());
      }
    }
    return instructorIds;
  }

  public List<Long> getClassIds() {
    List<Long> classIds = new ArrayList<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      for (ClassInfo classInfo : classInfoList) {
        classIds.add(classInfo.getClassId());
      }
    }
    return classIds;
  }

  public Map<Long, Long> getClassInstructorMap() {
    Map<Long, Long> classInstructorMap = new HashMap<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      for (ClassInfo classInfo : classInfoList) {
        classInstructorMap.put(classInfo.getClassId(), classInfo.getInstructorId());
      }
    }
    return classInstructorMap;
  }

  public Map<Long, Long> getInstructorClassMap() {
    Map<Long, Long> classInstructorMap = new HashMap<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      for (ClassInfo classInfo : classInfoList) {
        classInstructorMap.put(classInfo.getInstructorId(), classInfo.getClassId());
      }
    }
    return classInstructorMap;
  }

}
