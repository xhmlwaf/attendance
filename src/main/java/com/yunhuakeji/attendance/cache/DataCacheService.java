package com.yunhuakeji.attendance.cache;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class DataCacheService<T> {

    private List<T> list;

    public abstract List<T> listAll();

    private Timer timer;

    /**
     * 延迟20s执行
     */
    private long delay = 20 * 1000;

    public abstract long getPeriod();

    public DataCacheService() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                clearCache();
            }
        }, delay, getPeriod());
    }

    public List<T> list() {
        if (!CollectionUtils.isEmpty(list)) {
            return list;
        }
        list = listAll();
        return list;
    }

    public void clearCache() {
        if (!CollectionUtils.isEmpty(list)) {
            list.clear();
        }
    }
}
