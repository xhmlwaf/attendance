package com.yunhuakeji.attendance.cache;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.biz.impl.AnalysisBizImpl;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.service.baseservice.ClassInfoService;
import com.yunhuakeji.attendance.util.ListUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClassCacheService extends DataCacheService {

  private static final Logger logger = LoggerFactory.getLogger(ClassCacheService.class);

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
      return classInfoList.stream().collect(Collectors.toMap(ClassInfo::getClassId, Function.identity(), (k, v) -> v));
    }
    return Collections.EMPTY_MAP;
  }

  public static void main(String[] args) {
    List<ClassInfo> classInfoList = new ArrayList<>();
    ClassInfo c1 = new ClassInfo();
    c1.setClassId(1l);
    c1.setClassCode("name1");
    classInfoList.add(c1);

    ClassInfo c2 = new ClassInfo();
    c2.setClassId(2l);
    c2.setClassCode("name2");
    classInfoList.add(c2);

    System.out.println(JSON.toJSONString(classInfoList.stream().collect(Collectors.toMap(ClassInfo::getClassId, Function.identity(), (k, v) -> v))));
  }

  public List<Long> getInstructorIds() {
    List<Long> instructorIds = new ArrayList<>();
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      instructorIds = classInfoList.stream().map(e -> e.getInstructorId()).collect(Collectors.toList());
    }
    instructorIds = ListUtil.quChong(instructorIds);
    return instructorIds;
  }


  public List<Long> getClassIds() {
    List<ClassInfo> classInfoList = list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      return classInfoList.stream().map(e -> e.getClassId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
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
