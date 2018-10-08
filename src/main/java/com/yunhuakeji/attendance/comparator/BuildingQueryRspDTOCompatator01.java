package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dto.response.BuildingQueryRspDTO;
import java.io.Serializable;
import java.util.Comparator;

public class BuildingQueryRspDTOCompatator01 implements Comparator<BuildingQueryRspDTO>,
    Serializable {

  @Override
  public int compare(BuildingQueryRspDTO o1, BuildingQueryRspDTO o2) {
    return o1.getBuildingName().compareTo(o2.getBuildingName());
  }
}
