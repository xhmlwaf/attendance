package com.yunhuakeji.attendance.biz;

import org.apache.commons.lang3.StringUtils;

public class CommonHandlerUtil {

    public static String likeNameOrCode(String nameOrCode){
        if(StringUtils.isBlank(nameOrCode)){
            return null;
        }
        return "%"+nameOrCode.trim()+"%";
    }
}
