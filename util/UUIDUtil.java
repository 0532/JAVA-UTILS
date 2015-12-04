package com.haier.neusoft.o2o.common.util;

import java.util.UUID;

/**
 * Created by zhu_yn on 2015/7/6.
 * content :生成UUID
 */
public class UUIDUtil {
    /**
     * 生成UUID
     * @return
     */
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String result=uuid.toString();
        return result.toUpperCase().replaceAll("-","");
    }
}
