package com.yunhuakeji.attendance.controller.dto.response;

import io.swagger.annotations.ApiParam;

import java.util.List;
/**
 * 查寝详情
 */
public class DromCheckDetailQueryRspDTO {

    @ApiParam(name = "宿舍基本信息")
    private DormitoryQueryRspDTO baseInfo;

    @ApiParam(name = "学生信息列表")
    private List<DormQueryStudentRsqDTO> list;

}
