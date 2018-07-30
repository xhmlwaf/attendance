package com.yunhuakeji.attendance.cache;

import com.sun.tools.javac.util.List;
import com.yunhuakeji.attendance.dao.entity.AreaConfig;
import org.springframework.stereotype.Service;

@Service
public class PositionCache {

    private static final List<AreaConfig> areaConfigList = null;

    public static List<AreaConfig> getAreaConfigList() {

        return null;
    }

    public static void clearAreaConfig() {

        if (areaConfigList != null) {
            areaConfigList.clear();
        }
    }


}
