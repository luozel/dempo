package com.dev.testsos.sosgroup.utils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * JSON数据转换
 *
 * @author CJX
 * @data 2017年8月5日 下午4:44:47
 */
@Slf4j
public class MyJsonUtil {

    private MyJsonUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final String JAVA_LANG = "java.lang.";

    /**
     * 字符串转对象
     *
     * @param str
     * @param cs
     * @return
     */
    public static <T> T stringToObject(String str, Class<T> cs) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            /**
             * 该特性决定parser是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）。 如果该属性关闭，则如果遇到这些字符，则会抛出异常。
             * JSON标准说明书要求所有控制符必须使用引号，因此这是一个非标准的特性。
             *
             * 注意：默认时候，该属性关闭的。需要设置：JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS为true。
             *
             */
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
            t = objectMapper.readValue(str, cs);
        } catch (IOException e) {
            log.error("字符串转对象错误", e);
        }
        return t;
    }

    /**
     * 字符串转数组
     *
     * @param arrayStr
     * @param cs
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToList(Object arrayStr, Class<?> cs) {
        List<T> list = null;
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 该特性决定parser是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）。 如果该属性关闭，则如果遇到这些字符，则会抛出异常。
         * JSON标准说明书要求所有控制符必须使用引号，因此这是一个非标准的特性。
         *
         * 注意：默认时候，该属性关闭的。需要设置：JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS为true。
         *
         */
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, cs);
        try {
            list = objectMapper.readValue(arrayStr.toString(), javaType);
        } catch (IOException e) {
            log.error("字符串转List错误", e);
        }
        return list;
    }

    /**
     * 字符串转数组
     *
     * @param arrayStr
     * @param cs
     * @param <T>
     * @return
     */
    public static <T> List<T> stringToListthrowsException(Object arrayStr, Class<?> cs) throws IOException {
        List<T> list = null;
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, cs);
        list = objectMapper.readValue(arrayStr.toString(), javaType);
        return list;
    }

    /**
     * 对象/数组转字符串
     *
     * @param obj
     * @return
     */
    public static String objectToString(Object obj) {
        String className = obj.getClass().getName();
        String value = null;
        if (className.contains(JAVA_LANG)) {
            // 实现对基本类型的字符串转换
            value = obj.toString();
        } else {
            try {
                value = new ObjectMapper().writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                log.error("对象转字符串错误", e);
            }
        }
        return value;
    }

}