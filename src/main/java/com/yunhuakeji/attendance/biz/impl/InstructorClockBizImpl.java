package com.yunhuakeji.attendance.biz.impl;

import com.yunhuakeji.attendance.biz.BusinessUtil;
import com.yunhuakeji.attendance.biz.InstructorClockBiz;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.constants.ConfigConstants;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dao.basedao.model.User;
import com.yunhuakeji.attendance.dao.basedao.model.UserClass;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorCareCountStat;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClock;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClockCountStat;
import com.yunhuakeji.attendance.dto.request.InstructorClockReqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorClockStatRsqDTO;
import com.yunhuakeji.attendance.dto.response.InstructorStatRspDTO;
import com.yunhuakeji.attendance.service.baseservice.UserClassService;
import com.yunhuakeji.attendance.service.baseservice.UserService;
import com.yunhuakeji.attendance.service.bizservice.CareService;
import com.yunhuakeji.attendance.service.bizservice.InstructorClockService;
import com.yunhuakeji.attendance.util.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class InstructorClockBizImpl implements InstructorClockBiz {

  @Autowired
  private InstructorClockService instructorClockService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private UserClassService userClassService;

  @Autowired
  private CareService careService;

  @Autowired
  private UserService userService;

  @Override
  public Result<InstructorClockStatRsqDTO> statAllCount(Long instructorId) {
    int statCount = instructorClockService.statByInstructor(instructorId);
    InstructorClockStatRsqDTO rsqDTO = new InstructorClockStatRsqDTO();
    rsqDTO.setTotalClockCount(statCount);
    return Result.success(rsqDTO);
  }

  @Override
  public Result instructorClock(InstructorClockReqDTO req) {

    String qrCode = req.getQrCode();
    //TODO 校验二维码是否正确

    long yearMonthDay = DateUtil.currYYYYMMddToLong();
    List<InstructorClock> instructorClockList =
        instructorClockService.list(req.getInstructorId(), yearMonthDay);
    if (!CollectionUtils.isEmpty(instructorClockList)) {
      //TODO 已经打卡了
    }
    InstructorClock instructorClock = new InstructorClock();
    instructorClock.setClockTime(new Date());
    instructorClock.setInstructorId(req.getInstructorId());
    instructorClock.setStatDate((long) yearMonthDay);
    instructorClockService.save(instructorClock);

    return Result.success(null);
  }

  @Override
  public Result<List<String>> statByYearAndMonth(Long instructorId, Integer year, Integer month) {

    List<InstructorClock> instructorClockList = instructorClockService.list(instructorId, year, month);
    List<String> resultList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(instructorClockList)) {
      for (InstructorClock instructorClock : instructorClockList) {
        resultList.add(DateUtil.dateToStr(instructorClock.getClockTime(), DateUtil.DATESTYLE_YYYYMMDD_HH_MM_SS));
      }
    }
    return Result.success(resultList);
  }

  @Override
  public Result<Byte> getInstructorClockStatusByDay(Long instructorId) {
    List<InstructorClock> instructorClockList = instructorClockService.list(instructorId, DateUtil.currHhmmssToLong());
    if (CollectionUtils.isEmpty(instructorClockList)) {
      return Result.success(ConfigConstants.INSTRUCTOR_NOT_CLOCK);
    }
    return Result.success(ConfigConstants.INSTRUCTOR_CLOCK);
  }

  @Override
  public PagedResult<InstructorStatRspDTO> instructorStatPage(String nameOrCode,
                                                              Long orgId, Integer pageNo,
                                                              Integer pageSize,
                                                              String orderBy,
                                                              String descOrAsc) {

    Map<Long, Long> classInstructorMap = classCacheService.getClassInstructorMap();
    List<Long> instructorIds = classCacheService.getInstructorIds();
    List<Long> classIds = classCacheService.getClassIds();
    //可以算出负责学生
    List<UserClass> userClassList = userClassService.listStudentByClassIds(classIds);
    //打卡次数
    //    int statCount = instructorClockService.statByInstructor(instructorId);

    List<InstructorClockCountStat> instructorClockCountStatList =
        instructorClockService.instructorClockCountStatByIds(instructorIds);
    List<InstructorCareCountStat> instructorCareCountStatList = careService.instructorCareCountStat(instructorIds);

    List<InstructorStatRspDTO> instructorStatRspDTOList = new ArrayList<>();
    if (!CollectionUtils.isEmpty(instructorIds)) {
      List<User> userList = userService.selectByPrimaryKeyList(instructorIds);
      Map<Long, User> userMap = BusinessUtil.getUserMap(userList);
      for (Long id : instructorIds) {
        InstructorStatRspDTO dto = new InstructorStatRspDTO();
        dto.setUserId(id);
        User user = userMap.get(id);
        if(user!=null){
          dto.setName(user.getUserName());
          dto.setCode(user.getCode());
        }

      }
    }


    return null;
  }

  @Override
  public void statExportExcel(Long instructorId) {


  }
}
