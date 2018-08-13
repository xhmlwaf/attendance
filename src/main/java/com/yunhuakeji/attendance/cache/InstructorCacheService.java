package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.service.baseservice.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorCacheService extends DataCacheService {

  @Autowired
  private UserService userService;

  @Override
  public List listAll() {
    return null;
  }

  @Override
  public long getPeriod() {
    return 1000*60*60;
  }
}
