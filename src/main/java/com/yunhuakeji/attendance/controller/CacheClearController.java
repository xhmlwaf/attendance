package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.cache.BuildingCacheService;
import com.yunhuakeji.attendance.cache.ClassCacheService;
import com.yunhuakeji.attendance.cache.ClockAddressSettingCacheService;
import com.yunhuakeji.attendance.cache.ClockDaySettingCacheService;
import com.yunhuakeji.attendance.cache.ClockSettingCacheService;
import com.yunhuakeji.attendance.cache.DormitoryCacheService;
import com.yunhuakeji.attendance.cache.MajorCacheService;
import com.yunhuakeji.attendance.cache.OrgCacheService;
import com.yunhuakeji.attendance.constants.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@Api(value = "清除缓存")
@RestController
public class CacheClearController {

  @Autowired
  private BuildingCacheService buildingCacheService;

  @Autowired
  private ClassCacheService classCacheService;

  @Autowired
  private ClockAddressSettingCacheService clockAddressSettingCacheService;

  @Autowired
  private ClockDaySettingCacheService clockDaySettingCacheService;

  @Autowired
  private ClockSettingCacheService clockSettingCacheService;

  @Autowired
  private DormitoryCacheService dormitoryCacheService;

  @Autowired
  private MajorCacheService majorCacheService;

  @Autowired
  private OrgCacheService orgCacheService;


  @GetMapping("/cache-clear ")
  @ApiOperation(value = "根据换成类型清除缓存 1楼栋 2班级 3打卡地址 4打卡日期 5打卡配置 6宿舍 7专业 8院系")
  public Result cacheClear(
      @ApiParam(value = "缓存类型 1楼栋 2班级 3打卡地址 4打卡日期 5打卡配置 6宿舍 7专业 8院系", required = true)
      @RequestParam(name = "cacheType")
      @NotNull(message = "缓存类型不能为空")
      @Min(value = 1, message = "范围1-8")
      @Max(value = 8, message = "范围1-8")
          Integer cacheType
  ) {

    switch (cacheType) {
      case 1:
        buildingCacheService.clearCache();
        break;
      case 2:
        classCacheService.clearCache();
        break;
      case 3:
        clockAddressSettingCacheService.clearCache();
        break;
      case 4:
        clockDaySettingCacheService.clearCache();
        break;
      case 5:
        clockSettingCacheService.clearCache();
        break;
      case 6:
        dormitoryCacheService.clearCache();
        break;
      case 7:
        majorCacheService.clearCache();
        break;
      case 8:
        orgCacheService.clearCache();
        break;
      default:
        break;
    }
    return Result.success();
  }

}
