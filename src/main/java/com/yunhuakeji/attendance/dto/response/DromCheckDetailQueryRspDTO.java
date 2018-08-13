package com.yunhuakeji.attendance.dto.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查寝详情
 */
public class DromCheckDetailQueryRspDTO {

    @ApiModelProperty(value = "宿舍基本信息")
    private DormitorySimpleRspDTO baseInfo;

    @ApiModelProperty(value = "学生信息列表")
    private List<DormQueryStudentRsqDTO> list;

}
