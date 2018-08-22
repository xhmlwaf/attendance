package com.yunhuakeji.attendance.cache;

import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 打卡缓存队列
 */
public class StudentClockCache {

  public static final Logger logger = LoggerFactory.getLogger(StudentClockCache.class);

  /**
   * 队列最大长度
   */
  private static final int MAX_QUEUE_SIZE = 50000;

  public static final BlockingQueue<StudentClock> studentClockBlockingQueue = new LinkedBlockingDeque<>(MAX_QUEUE_SIZE);

  public static void put(StudentClock studentClock) {
    try {
      studentClockBlockingQueue.put(studentClock);
    } catch (InterruptedException e) {
      logger.error("放入队列出错.", e);
    }
  }


}
