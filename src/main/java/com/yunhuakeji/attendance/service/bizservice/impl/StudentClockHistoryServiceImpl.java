package com.yunhuakeji.attendance.service.bizservice.impl;

import com.yunhuakeji.attendance.dao.bizdao.StudentClockHistoryMapper;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockHistory;
import com.yunhuakeji.attendance.service.bizservice.StudentClockHistoryService;
import com.yunhuakeji.attendance.util.DateUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class StudentClockHistoryServiceImpl implements StudentClockHistoryService {

  @Resource
  private StudentClockHistoryMapper studentClockHistoryMapper;

  @Override
  public List<StudentClockHistory> list(long studentId, long statDate) {
    Example example = new Example(StudentClockHistory.class);
    Example.Criteria criteria = example.createCriteria();
    criteria.andEqualTo("userId", studentId);
    criteria.andEqualTo("statDate", statDate);
    return studentClockHistoryMapper.selectByExample(example);
  }

  @Override
  public void batchInsert(List<StudentClockHistory> studentClockHistoryList) {
    long startUuid = DateUtil.uuid();
    for (StudentClockHistory studentClockHistory : studentClockHistoryList) {
      studentClockHistory.setId(startUuid);
      startUuid++;
    }
    studentClockHistoryMapper.insertBatchSelective(studentClockHistoryList);
  }
}
