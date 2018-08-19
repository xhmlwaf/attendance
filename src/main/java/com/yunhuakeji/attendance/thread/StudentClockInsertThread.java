package com.yunhuakeji.attendance.thread;

import com.alibaba.fastjson.JSON;
import com.yunhuakeji.attendance.cache.StudentClockCache;
import com.yunhuakeji.attendance.dao.bizdao.model.StudentClock;
import com.yunhuakeji.attendance.service.bizservice.StudentClockService;
import com.yunhuakeji.attendance.service.bizservice.impl.StudentClockServiceImpl;
import com.yunhuakeji.attendance.util.ApplicationUtils;
import com.yunhuakeji.attendance.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentClockInsertThread implements Runnable {

    public static final Logger logger = LoggerFactory.getLogger(StudentClockInsertThread.class);

    /**
     * 批量插入数量
     */
    private static final int BATCH_INSERT_SIZE = 50;
    /**
     * 最长等待时间
     */
    private static final int WAIT_SECONDS = 4;

    @Override
    public void run() {

        List<StudentClock> studentClockList = new ArrayList<>();
        long lastTime = System.currentTimeMillis();

        try {
            while (true) {
                StudentClock studentClock = StudentClockCache.take();
                if (studentClock == null) {
                    continue;
                }
                long currTime = System.currentTimeMillis();

                studentClockList.add(studentClock);
                if (studentClockList.size() >= BATCH_INSERT_SIZE || currTime - lastTime > WAIT_SECONDS * 1000) {
                    StudentClockService studentClockService = ApplicationUtils.getBean(StudentClockServiceImpl.class);
                    //TODO 好多参数要查询

                    //TODO 历史表都要加一条记录
                    studentClock.setId(DateUtil.uuid());
                    studentClockService.batchInsert(studentClockList);
                    studentClockList.clear();
                    lastTime = System.currentTimeMillis();
                }

            }

        } catch (Exception e) {
            logger.error("插入数据异常.data:" + JSON.toJSONString(studentClockList), e);
        }

    }


}
