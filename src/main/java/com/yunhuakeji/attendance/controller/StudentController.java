package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.dto.response.StudentManageQueryRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;

@Api(value = "学生操作接口")
@Controller
public class StudentController {

    @GetMapping("/student/{id}")
    @ApiOperation(value = "根据学生ID查询学生基本信息")
    public Result<StudentManageQueryRspDTO> getStudentBaseInfo(
            @ApiParam(name = "学生ID", required = true)
            @PathVariable(name = "id")
            @NotBlank(message = "学生ID不能为空")
                    String id
    ) {

        return null;
    }
}
