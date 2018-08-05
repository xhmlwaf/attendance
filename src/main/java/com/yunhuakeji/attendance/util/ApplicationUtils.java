package com.yunhuakeji.attendance.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationUtils implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
    {
        ApplicationUtils.applicationContext = applicationContext;
    }
    
    public static <T> T getBean(Class<T> className)
    {
        return applicationContext.getBean(className);
    }
}
