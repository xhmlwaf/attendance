package com.yunhuakeji.attendance.thread.pool;

import com.yunhuakeji.attendance.thread.StayoutStatThread;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class StayoutStatThreadPool implements ApplicationListener<ContextRefreshedEvent> {

  private static final int THREAD_SIZE = 1;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
    for (int i = 0; i < THREAD_SIZE; i++) {
      executorService.execute(new StayoutStatThread());
    }
  }
}
