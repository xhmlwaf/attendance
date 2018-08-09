package com.yunhuakeji.attendance;

import com.yunhuakeji.attendance.config.DynamicDataSourceRegister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.yunhuakeji.attendance.dao.*"})
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
public class AttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceApplication.class, args);
    }
}
