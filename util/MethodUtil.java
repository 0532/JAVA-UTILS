package com.haier.neusoft.o2o.common.util;

import io.terminus.common.model.Response;

/**
 * 方法工具类.
 *
 * @author will Tong
 * @see
 */
public class MethodUtil {

    /**
     * 根据dubbo返回结果判断抛出异常还是继续处理
     * @param response dubbo返回结果
     * @return dubbo实体
     * @throws Exception
     */
    public static Object convertResponse(Response response)throws Exception{
        if(response.isSuccess()){
            return response.getResult();
        }else{
            throw new Exception(String.format(response.getError()));
        }
    }

    public static Object catchResponse(ResponseAction action)throws Exception{
        try {
            Response response=action.execute();
            if(response.isSuccess()){
               return response.getResult();
            }else{
                throw new Exception(response.getError());
            }
        }catch (Exception e){
            action.error(e);
            throw new Exception(e);
        }
    }

    public interface ResponseAction{
        public Response execute() throws Exception;
        public void error(Exception e);
    }
}
