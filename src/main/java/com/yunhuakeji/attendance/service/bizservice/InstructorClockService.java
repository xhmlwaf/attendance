package com.yunhuakeji.attendance.service.bizservice;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClock;
import com.yunhuakeji.attendance.dao.bizdao.model.InstructorClockCountStat;

import java.util.Date;
import java.util.List;

public interface InstructorClockService {

  int statByInstructor(long instructorId);

  List<InstructorClock> list(long instructorId, long clockDate);

  List<InstructorClock> list(long instructorId, int year, int month);

  int save(InstructorClock instructorClock);

  List<InstructorClockCountStat> instructorClockCountStatByIds(List<Long> instructorIds);

  PageInfo<InstructorClock> page(Long instructorId, int pageNo, int pageSize);
}
