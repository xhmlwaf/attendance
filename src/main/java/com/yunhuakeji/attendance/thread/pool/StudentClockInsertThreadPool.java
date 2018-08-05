package com.yunhuakeji.attendance.thread.pool;

import com.yunhuakeji.attendance.thread.StudentClockInsertThread;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class StudentClockInsertThreadPool {

    private static final int THREAD_SIZE = 2;

    public StudentClockInsertThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);
        for (int i = 0; i < THREAD_SIZE; i++) {
            executorService.execute(new StudentClockInsertThread());
        }
    }
}
