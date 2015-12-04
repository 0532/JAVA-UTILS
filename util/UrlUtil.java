package com.haier.neusoft.o2o.common.util;

import com.google.common.base.Joiner;

import java.util.Map;

/**
 * URL相关操作类.
 *
 * @author will Tong
 */
public class UrlUtil {
    /**
     * 根据map里面的内容生成url参数.
     * <pre>
     *     createUrlParameter()
     * </pre>
     *
     * @param params 参数map
     * @return url参数
     */
    public static String createUrlParameter(Map<String,String> params){
        if(params.size()==0){
            return "";
        }
        StringBuffer result=new StringBuffer();
        for(Map.Entry<String, String> iterator: params.entrySet()){
            result.append(Joiner.on("=").join(iterator.getKey(), iterator.getValue())).append("&");
        }
        if(result.toString().endsWith("&")){
            result.deleteCharAt(result.length()-1);
        }
        return result.toString();
    }
}
