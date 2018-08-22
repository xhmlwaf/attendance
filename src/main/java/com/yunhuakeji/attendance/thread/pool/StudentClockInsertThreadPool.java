package com.yunhuakeji.attendance.thread.pool;

import com.yunhuakeji.attendance.thread.StudentClockInsertThread;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class StudentClockInsertThreadPool implements ApplicationListener<ContextRefreshedEvent> {

  /**
   * 线程数
   */
  private static final int THREAD_SIZE = 3;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
    for (int i = 0; i < THREAD_SIZE; i++) {
      executorService.execute(new StudentClockInsertThread());
    }
  }
}
