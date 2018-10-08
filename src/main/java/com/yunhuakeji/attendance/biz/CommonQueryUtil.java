package com.yunhuakeji.attendance.biz;

import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.util.ApplicationUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

public class CommonQueryUtil {


  public static List<Long> getClassIdsByOrgId(Long orgId) {
    if (orgId == null) {
      return null;
    }
    MajorCacheService majorCacheService = ApplicationUtils.getBean(MajorCacheService.class);
    List<MajorInfo> majorInfoList = majorCacheService.list();
    List<Long> majorIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(majorInfoList)) {
      for (MajorInfo majorInfo : majorInfoList) {
        if (majorInfo.getOrgId().equals(orgId)) {
          majorIds.add(majorInfo.getMajorId());
        }
      }
    }
    List<Long> classIds = new ArrayList<>();
    getLastClassIds(majorIds, classIds);
    return classIds;
  }

  public static List<Long> getClassIdsByOrgIds(List<Long> orgIds) {
    if (CollectionUtils.isEmpty(orgIds)) {
      return null;
    }
    MajorCacheService majorCacheService = ApplicationUtils.getBean(MajorCacheService.class);
    List<MajorInfo> majorInfoList = majorCacheService.list();
    List<Long> majorIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(majorInfoList)) {
      for (MajorInfo majorInfo : majorInfoList) {
        if (orgIds.contains(majorInfo.getOrgId())) {
          majorIds.add(majorInfo.getMajorId());
        }
      }
    }
    List<Long> classIds = new ArrayList<>();
    getLastClassIds(majorIds, classIds);
    return classIds;
  }

  private static void getLastClassIds(List<Long> majorIds, List<Long> classIds) {
    if (!CollectionUtils.isEmpty(majorIds)) {
      ClassCacheService classCacheService = ApplicationUtils.getBean(ClassCacheService.class);
      List<ClassInfo> classInfoList = classCacheService.list();
      if (!CollectionUtils.isEmpty(classInfoList)) {
        for (ClassInfo classInfo : classInfoList) {
          if (majorIds.contains(classInfo.getMajorId())) {
            classIds.add(classInfo.getClassId());
          }
        }
      }
    }
  }

  public static List<Long> getClassIdsByMajorId(Long majorId) {
    if (majorId == null) {
      return null;
    }
    ClassCacheService classCacheService = ApplicationUtils.getBean(ClassCacheService.class);
    List<ClassInfo> classInfoList = classCacheService.list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      return classInfoList.stream().map(e -> e.getClassId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public static List<Long> getClassIdsByInstructorId(Long instructorId) {
    if (instructorId == null) {
      return null;
    }
    ClassCacheService classCacheService = ApplicationUtils.getBean(ClassCacheService.class);
    List<ClassInfo> classInfoList = classCacheService.list();
    if (!CollectionUtils.isEmpty(classInfoList)) {
      return classInfoList.stream().filter(e -> e.getInstructorId().equals(instructorId))
          .map(e -> e.getClassId()).collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

}
