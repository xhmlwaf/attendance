package com.yunhuakeji.attendance.controller;

import com.yunhuakeji.attendance.constants.Result;
import com.yunhuakeji.attendance.controller.dto.response.DormQueryRspDTO;
import com.yunhuakeji.attendance.controller.dto.response.FloorQueryRspDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@Api(value = "宿舍管理操作接口")
@Controller
public class DormitoryController {


    @GetMapping("/dormitorys/{id}/floors")
    @ApiOperation(value = "根据宿舍ID查询楼层列表")
    public Result<FloorQueryRspDTO> listFloorByDormitoryId(
            @ApiParam(name = "宿舍楼ID", required = true)
            @RequestParam(name = "dormitoryId")
            @NotBlank(message = "宿舍楼ID不能为空")
                    String dormitoryId
    ) {
        return null;
    }

    @GetMapping("/floors/{id}/dorms")
    @ApiOperation(value = "根据楼层ID查询宿舍ID")
    public Result<DormQueryRspDTO> listDormByFloorId(
            @ApiParam(name = "楼层ID", required = true)
            @RequestParam(name = "floorId")
            @NotBlank(message = "楼层ID不能为空")
                    String floorId
    ) {
        return null;
    }
}
