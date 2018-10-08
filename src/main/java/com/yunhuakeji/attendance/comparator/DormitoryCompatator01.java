package com.yunhuakeji.attendance.comparator;

import com.yunhuakeji.attendance.dao.basedao.model.DormitoryInfo;
import java.io.Serializable;
import java.util.Comparator;

////根据宿舍code排序
public class DormitoryCompatator01 implements Comparator<DormitoryInfo>, Serializable {

  @Override
  public int compare(DormitoryInfo o1, DormitoryInfo o2) {
    return o1.getCode().compareTo(o2.getCode());
  }
}
