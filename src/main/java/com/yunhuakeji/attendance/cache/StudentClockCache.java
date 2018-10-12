package com.yunhuakeji.attendance.cache;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClockDTO;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentClockCache {

  public static final Logger logger = LoggerFactory.getLogger(StudentClockCache.class);

  private static final int MAX_QUEUE_SIZE = 50000;

  public static final BlockingQueue<StudentClockDTO> studentClockBlockingQueue = new LinkedBlockingDeque<>(
      MAX_QUEUE_SIZE);

  public static void put(StudentClockDTO studentClock) {
    logger.info("put queue:" + JSON.toJSONString(studentClock));
    try {
      studentClockBlockingQueue.put(studentClock);
    } catch (InterruptedException e) {
      logger.error(e.getMessage(), e);
    }
  }


}
