package com.yunhuakeji.attendance.dto.response;

import java.util.List;

public class DormitoryClockDetailStatDTO extends DormitoryClockStatDTO {

  List<DormitoryStudentStatRspDTO> detailList;

  public List<DormitoryStudentStatRspDTO> getDetailList() {
    return detailList;
  }

  public void setDetailList(List<DormitoryStudentStatRspDTO> detailList) {
    this.detailList = detailList;
  }
}
