package com.yunhuakeji.attendance.thread;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.biz.ConvertUtil;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.DormitoryCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.StudentClockCache;
import com.yunhuakeji.attendance.dao.basedao.model.ClassInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import com.yunhuakeji.attendance.dao.basedao.model.DormitoryUser;
import com.yunhuakeji.attendance.dao.basedao.model.MajorInfo;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockHistory;
import com.yunhuakeji.attendance.enums.ClockStatus;
import com.yunhuakeji.attendance.service.baseservice.DormitoryUserService;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.baseservice.impl.DormitoryUserServiceImpl;
import com.yunhuakeji.attendance.service.baseservice.impl.UserClassServiceImpl;
import com.yunhuakeji.attendance.service.baseservice.impl.UserServiceImpl;
import com.yunhuakeji.attendance.service.bizservice.StudentClockHistoryService;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.impl.StudentClockHistoryServiceImpl;
import com.yunhuakeji.attendance.service.bizservice.impl.StudentClockServiceImpl;
import com.yunhuakeji.attendance.util.ApplicationUtils;
import com.yunhuakeji.attendance.util.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class StudentClockInsertThread implements Runnable {

  public static final Logger logger = LoggerFactory.getLogger(StudentClockInsertThread.class);

  /**
   * 批量插入数量
   */
  private static final int BATCH_INSERT_SIZE = 50;
  /**
   * 最长等待时间
   */
  private static final int WAIT_SECONDS = 4;

  @Override
  public void run() {

    List<StudentClock> studentClockList = new ArrayList<>();
    long lastTime = System.currentTimeMillis();

    StudentClockService studentClockService = ApplicationUtils.getBean(StudentClockServiceImpl.class);
    StudentClockHistoryService studentClockHistoryService = ApplicationUtils.getBean(StudentClockHistoryServiceImpl.class);

    UserClassService userClassService = ApplicationUtils.getBean(UserClassServiceImpl.class);
    ClassCacheService classCacheService = ApplicationUtils.getBean(ClassCacheService.class);
    MajorCacheService majorCacheService = ApplicationUtils.getBean(MajorCacheService.class);
    UserService userService = ApplicationUtils.getBean(UserServiceImpl.class);
    DormitoryUserService dormitoryUserService = ApplicationUtils.getBean(DormitoryUserServiceImpl.class);
    DormitoryCacheService dormitoryCacheService = ApplicationUtils.getBean(DormitoryCacheService.class);
    try {
      while (true) {
        StudentClock studentClock = StudentClockCache.studentClockBlockingQueue.poll(500, TimeUnit.MILLISECONDS);
        if (studentClock == null) {
          studentClockList.add(studentClock);
        }
        long currTime = System.currentTimeMillis();

        if (studentClockList.size() >= BATCH_INSERT_SIZE || currTime - lastTime >= WAIT_SECONDS * 1000) {

          if (!CollectionUtils.isEmpty(studentClockList)) {
            List<Long> studentIds = ConvertUtil.getStudentIds(studentClockList);
            List<UserClass> userClassList = userClassService.listByUserIds(studentIds);
            Map<Long, Long> userClassMap = ConvertUtil.getUserClassMap(userClassList);
            Map<Long, ClassInfo> classInfoMap = classCacheService.getClassInfoMap();
            Map<Long, MajorInfo> majorInfoMap = majorCacheService.getMajorInfoMap();
            List<User> userList = userService.selectByPrimaryKeyList(studentIds);
            Map<Long, User> userMap = ConvertUtil.getUserMap(userList);
            List<DormitoryUser> dormitoryUserList = dormitoryUserService.listByUserIds(studentIds);
            Map<Long, Long> userDormitoryMap = ConvertUtil.getUserDormitoryMap(dormitoryUserList);
            Map<Long, DormitoryInfo> dormitoryInfoMap =dormitoryCacheService.getDormitoryMap();
            long startUuid = DateUtil.uuid();
            List<StudentClockHistory> studentClockHistoryList = new ArrayList<>();
            for (StudentClock clock : studentClockList) {
              long studentId = clock.getUserId();
              Date d = new Date();
              clock.setCreateTime(d);
              clock.setClockTime(d);
              clock.setClockDate(DateUtil.getYearMonthDayByDate(d));
              clock.setUpdateTime(d);
              clock.setId(startUuid++);
              clock.setClockStatus(ClockStatus.CLOCK.getType());

              Long dormitoryId = userDormitoryMap.get(studentId);
              if (dormitoryId != null) {
                DormitoryInfo dormitoryInfo = dormitoryInfoMap.get(dormitoryId);
                if(dormitoryInfo!=null){
                  clock.setBuildingId(dormitoryInfo.getBuildingId());
                }
              }
              User user = userMap.get(studentId);
              if (user != null) {
                clock.setGender(user.getGender());
              }
              Long classId = userClassMap.get(studentId);
              clock.setClassId(classId);
              ClassInfo classInfo = classInfoMap.get(classId);
              if (classInfo != null) {
                clock.setInstructorId(classInfo.getInstructorId());
                clock.setMajorId(classInfo.getMajorId());
                MajorInfo majorInfo = majorInfoMap.get(classInfo.getMajorId());
                if (majorInfo != null) {
                  clock.setOrgId(majorInfo.getOrgId());
                }
              }

              StudentClockHistory studentClockHistory = new StudentClockHistory();
              studentClockHistory.setOperatorId(studentId);
              if(user!=null){
                studentClockHistory.setOperatorName(user.getUserName());
              }
              studentClockHistory.setUserId(studentId);
              studentClockHistory.setStatDate(DateUtil.getYearMonthDayByDate(d));
              studentClockHistory.setOperateTime(d);
              studentClockHistory.setClockStatus(ClockStatus.CLOCK.getType());
              studentClockHistory.setId(startUuid++);
              studentClockHistory.setAppName("学生打卡");
              studentClockHistoryList.add(studentClockHistory);

            }
            studentClockHistoryService.batchInsert(studentClockHistoryList);
            studentClockService.batchInsert(studentClockList);
            studentClockList.clear();
          }
          lastTime = System.currentTimeMillis();
        }

      }

    } catch (Exception e) {
      logger.error("插入数据异常.data:" + JSON.toJSONString(studentClockList), e);
    }

  }


}
