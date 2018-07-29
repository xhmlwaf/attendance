package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.PagedResult;
import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.request.CareUpdateReqDTO;
import com.yunhuakeji.attendance.controller.dto.response.CareQueryRspDTO;
import com.yunhuakeji.attendance.controller.dto.response.StudentClockQueryRsqDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(value = "关怀操作接口")
@Controller
public class CareController {

    @GetMapping("/care")
    @ApiOperation(value = "统计学生累计晚归，到勤，未归次数")
    public PagedResult<CareQueryRspDTO> listAll(
            @ApiParam(name = "辅导员ID", required = true)
            @RequestParam(name = "instructorId")
            @NotBlank(message = "辅导员ID不能为空")
                    String instructorId,
            @ApiParam(name = "关怀类型，1待关怀 2已关怀", required = true)
            @RequestParam(name = "careType")
            @NotNull(message = "年份不能为空")
                    Integer careType
    ) {
        return null;
    }

    @PutMapping("/care")
    @ApiOperation(value = "提交关怀结果")
    public Result updateCare(CareUpdateReqDTO reqDTO) {
        return null;
    }


}
