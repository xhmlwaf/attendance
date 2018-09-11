package com.yunhuakeji.attendance.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Validated
@Api(value = "门禁打卡历史操作接口")
@RestController
public class EntranceGuardHistoryController {
}
