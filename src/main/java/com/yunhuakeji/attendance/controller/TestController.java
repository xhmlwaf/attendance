package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.service.bizservice.RedisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

@Controller
public class TestController {

  @Autowired
  private RedisService redisService;

  @GetMapping("/test")
  public void test() {

    System.out.println(redisService.getAndSet("test_str_a", "xxxxxxx"));
    redisService.expire("test_str_a", 10, TimeUnit.SECONDS);

  }
}
