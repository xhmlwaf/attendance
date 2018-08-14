package com.yunhuakeji.attendance.service.bizservice;

import com.github.pagehelper.PageInfo;
import com.yunhuakeji.attendance.dao.bizdao.model.Care;

public interface CareService {

  PageInfo pageByInstructor(long instructorId, Byte careStatus, int pageNo, int pageSize);

  void update(Care care);

  PageInfo pageByStudent(long studentId,int pageNo, int pageSize);
}
