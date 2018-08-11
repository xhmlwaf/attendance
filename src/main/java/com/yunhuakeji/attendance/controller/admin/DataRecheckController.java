package com.yunhuakeji.attendance.controller.admin;

import com.yunhuakeji.attendance.biz.DataRecheckBiz;
import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.dto.response.StudentClockCareStatRspDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "数据复核模块接口")
@Controller
public class DataRecheckController {

    @Autowired
    private DataRecheckBiz dataRecheckBiz;

    @PutMapping("/data-recheck/student-clock-care-stat")
    @ApiOperation(value = "系统配置")
    public PagedResult<StudentClockCareStatRspDTO> studentClockStatQueryPage(
            @ApiParam(name = "机构ID")
            @RequestParam(name = "orgId", required = false)
                    Long orgId,
            @ApiParam(name = "专业ID")
            @RequestParam(name = "majorId", required = false)
                    Long majorId,
            @ApiParam(name = "辅导员ID")
            @RequestParam(name = "instructor", required = false)
                    Long instructorId,
            @ApiParam(name = "楼栋ID")
            @RequestParam(name = "buildingId", required = false)
                    Long buildingId,
            @ApiParam(name = "姓名或学号（姓名或学号不为空时将忽略其他查询条件）")
            @RequestParam(name = "nameOrCode", required = false)
                    String nameOrCode,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1")
            @Min(value = 1, message = "当前页码最小为1") Integer pageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10")
            @Min(value = 1, message = "每页数量最小为1") Integer pageSize
    ) {
        return dataRecheckBiz.studentClockStatQueryPage(orgId, majorId, instructorId, buildingId, nameOrCode, pageNo, pageSize);
    }


}
