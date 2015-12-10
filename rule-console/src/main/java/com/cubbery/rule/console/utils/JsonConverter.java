/**
 * Copyright (c) 2015, www.cubbery.com. All rights reserved.
 */
package com.cubbery.rule.console.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * <b>项目名</b>： rule-parent <br>
 * <b>包名称</b>： com.cubbery.rule.console.utils <br>
 * <b>类名称</b>： JsonConverter <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubber[百墨]</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/12/10 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class JsonConverter {
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private JsonConverter() {
    }

    /**
     * @param obj
     * @return
     */
    public synchronized static <T> String serialize(T obj) {
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T deserialize(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserialize(String json, Type type) {
        try {
            json = json.trim();
            T t = (T) gson.fromJson(json, type);
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return
     */
    public static Gson gsonInstance() {
        return gson;
    }

    public static Map<String, String> map(String json) {
        return gson.fromJson(json, Map.class);
    }
}

