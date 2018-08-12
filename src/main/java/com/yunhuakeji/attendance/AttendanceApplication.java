package com.yunhuakeji.attendance;

import com.yunhuakeji.attendance.config.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.yunhuakeji.attendance.dao.*"})
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
@EnableSwagger2
public class AttendanceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AttendanceApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AttendanceApplication.class);
    }

}
