package com.haier.neusoft.o2o.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * json操作工具类.
 * @author Will Tong
 */
@Component
@Slf4j
public class JsonUtil {


    private static ObjectMapper mapper;

    /**
     * 初始化
     */
    private static void init(){
        if(mapper==null){
            mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.registerModule(new GuavaModule());
        }
    }

    /**
     * json转实体
     * @param jsonString json字符串
     * @param clazz 实体类
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            init();
            return mapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            log.error(Throwables.getStackTraceAsString(e));
            return null;
        }
    }

    /**
     * 实体转json
     * @param object 实体类
     * @return json字符串
     */
    public static String toJson(Object object) {
        try {
            init();
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.warn("write to json string error:" + object, e);
        }
        return null;
    }
}
