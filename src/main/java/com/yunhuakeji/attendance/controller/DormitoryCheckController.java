package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.biz.DormitoryBiz;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.request.DormitoryCheckOverReqDTO;
import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckDayStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryCheckWeekStatRspDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockDetailStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitoryClockStatDTO;
import com.yunhuakeji.attendance.dto.response.DormitorySimpleRspDTO;
import com.yunhuakeji.attendance.dto.response.StudentDormitoryRsqDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "查寝操作接口")
@Controller
public class DormitoryCheckController {

    @Autowired
    private DormitoryBiz dormitoryBiz;

    @GetMapping("/dormitory/all")
    @ApiOperation(value = "根据楼栋ID和层数（层数可为空）查询宿舍列表")
    public Result<List<DormitorySimpleRspDTO>> listDormitory(
            @ApiParam(name = "楼栋ID", required = true)
            @RequestParam(name = "buildingId")
            @NotNull(message = "楼栋ID不能为空")
                    Long buildingId,
            @ApiParam(name = "层数")
            @RequestParam(name = "floorNumber", required = false)
            @NotNull(message = "层数不能为空")
                    Integer floorNumber
    ) {
        return dormitoryBiz.listDormitory(buildingId, floorNumber);
    }

    @GetMapping("/dormitory-building/all/app")
    @ApiOperation(value = "根据用户查询宿舍楼")
    public Result<List<BuildingQueryRspDTO>> listBuildingForApp(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotNull(message = "用户ID不能为空")
                    Long userId,
            @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
            @RequestParam(name = "appType")
            @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
            @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
            @NotNull(message = "app类型")
                    Byte appType
    ) {

        return dormitoryBiz.listBuildingForApp(userId, appType);
    }

    @GetMapping("/dormitory/all/app")
    @ApiOperation(value = "根据用户查询宿舍楼下的宿舍")
    public Result<List<DormitorySimpleRspDTO>> listDormitoryForApp(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotNull(message = "用户ID不能为空")
                    Long userId,
            @ApiParam(name = "楼栋ID", required = true)
            @RequestParam(name = "buildingId")
            @NotNull(message = "楼栋ID不能为空")
                    Long buildingId,
            @ApiParam(name = "楼层", required = true)
            @RequestParam(name = "floorNumber")
            @NotNull(message = "楼层")
                    Integer floorNumber

    ) {

        return null;
        //return dormitoryBiz.listDormitoryForApp(userId, appType, buildingId, floorNumber);
    }

    @GetMapping("/dormitory/all/app")
    @ApiOperation(value = "根据用户查询宿舍楼下的宿舍")
    public Result<List<DormitoryClockStatDTO>> listDormitoryClockStatForApp(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotNull(message = "用户ID不能为空")
                    Long userId,
//      @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
//      @RequestParam(name = "appType")
//      @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
//      @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
//      @NotNull(message = "app类型")
//          Byte appType,
            @ApiParam(name = "楼栋ID", required = true)
            @RequestParam(name = "buildingId")
            @NotNull(message = "楼栋ID不能为空")
                    Long buildingId,
            @ApiParam(name = "楼层")
            @RequestParam(name = "floorNumber", required = false)
                    Integer floorNumber,
            @ApiParam(name = "宿舍ID")
            @RequestParam(name = "dormitoryId", required = false)
                    Long dormitoryId

    ) {

        return null;
        //return dormitoryBiz.listDormitoryClockStatForApp(userId, appType, buildingId, floorNumber, dormitoryId);
    }

    @GetMapping("/dormitory/{dormitoryId}/detail/app")
    @ApiOperation(value = "根据用户查询宿舍楼下的宿舍")
    public Result<DormitoryClockDetailStatDTO> getDormitoryClockDetailStatForApp(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotNull(message = "用户ID不能为空")
                    Long userId,
//      @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
//      @RequestParam(name = "appType")
//      @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
//      @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
//      @NotNull(message = "app类型")
//          Byte appType,
            @ApiParam(name = "宿舍ID", required = true)
            @PathVariable(name = "dormitoryId")
                    Long dormitoryId

    ) {

        return null;
    }

    @GetMapping("/dormitory-check/day-stat")
    @ApiOperation(value = "查寝日统计")
    public Result<DormitoryCheckDayStatRspDTO> dayStat(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotNull(message = "用户ID不能为空")
                    Long userId,
//      @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
//      @RequestParam(name = "appType")
//      @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
//      @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
//      @NotNull(message = "app类型")
//          Byte appType,
            @ApiParam(name = "年份", required = true)
            @RequestParam(name = "year")
            @Min(value = 1000, message = "年份参数错误")
            @Max(value = 9999, message = "年份参数错误")
            @NotNull(message = "年份不能为空")
                    Integer year,
            @ApiParam(name = "月份", required = true)
            @RequestParam(name = "month")
            @Min(value = 1, message = "月份参数错误")
            @Max(value = 12, message = "月份参数错误")
            @NotNull(message = "月份不能为空")
                    Integer month,
            @ApiParam(name = "日期", required = true)
            @RequestParam(name = "day")
            @Min(value = 1, message = "日期参数错误")
            @Max(value = 31, message = "日期参数错误")
            @NotNull(message = "日期不能为空")
                    Integer day
    ) {
        return null;
    }

    @GetMapping("/dormitory-check/week-stat")
    @ApiOperation(value = "查寝周统计")
    public Result<DormitoryCheckWeekStatRspDTO> weekStat(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotNull(message = "用户ID不能为空")
                    Long userId,
//      @ApiParam(name = "app类型 1辅导员/2宿舍员/3学生处", required = true)
//      @RequestParam(name = "appType")
//      @Min(value = 1, message = "App类型 1辅导员/2宿舍员/3学生处")
//      @Max(value = 3, message = "App类型 1辅导员/2宿舍员/3学生处")
//      @NotNull(message = "app类型")
//          Byte appType,
            @ApiParam(name = "周数", required = true)
            @RequestParam(name = "weekNumber")
            @NotNull(message = "周数不能为空")
                    Integer weekNumber
    ) {
        return null;
    }

    @GetMapping("/dormitory-check/day-stat/student")
    @ApiOperation(value = "日统计学生列表")
    public Result<DormitoryCheckDayStatRspDTO> dayStatStudentList(
            @ApiParam(name = "用户ID", required = true)
            @RequestParam(name = "userId")
            @NotNull(message = "用户ID不能为空")
                    Long userId,
            @ApiParam(name = "年份", required = true)
            @RequestParam(name = "year")
            @Min(value = 1000, message = "年份参数错误")
            @Max(value = 9999, message = "年份参数错误")
            @NotNull(message = "年份不能为空")
                    Integer year,
            @ApiParam(name = "月份", required = true)
            @RequestParam(name = "month")
            @Min(value = 1, message = "月份参数错误")
            @Max(value = 12, message = "月份参数错误")
            @NotNull(message = "月份不能为空")
                    Integer month,
            @ApiParam(name = "日期", required = true)
            @RequestParam(name = "day")
            @Min(value = 1, message = "日期参数错误")
            @Max(value = 31, message = "日期参数错误")
            @NotNull(message = "日期不能为空")
                    Integer day,
            @ApiParam(name = "考勤状态", required = true)
            @RequestParam(name = "clockStatus")
            @NotNull(message = "考勤状态不能为空")
                    Byte clockStatus


    ) {
        return null;
    }


    @GetMapping("/dormitory-check/query-name-code")
    @ApiOperation(value = "根据姓名和学号查询学生列表并带上宿舍ID")
    public Result<List<StudentDormitoryRsqDTO>> queryStudent(
            @ApiParam(name = "姓名")
            @RequestParam(name = "name", required = false)
                    String name,
            @ApiParam(name = "工号")
            @RequestParam(name = "code", required = false)
                    String code
    ) {

        return null;
    }


    @PostMapping("/dormitory-check")
    @ApiOperation(value = "结束查寝")
    public Result addDormitoryCheck(@Valid @RequestBody DormitoryCheckOverReqDTO reqDTO) {
        return null;
    }
}
