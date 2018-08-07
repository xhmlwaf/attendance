package com.yunhuakeji.attendance.dto.response;

import java.util.List;

import io.swagger.annotations.ApiParam;

/**
 * 查寝详情
 */
public class DromCheckDetailQueryRspDTO {

    @ApiParam(name = "宿舍基本信息")
    private DormitorySimpleRspDTO baseInfo;

    @ApiParam(name = "学生信息列表")
    private List<DormQueryStudentRsqDTO> list;

}
