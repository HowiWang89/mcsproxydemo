package com.huifu.mcsproxydemo.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author :  shulin.huang
 */
public class CommonStringUtils {

    public static SortedMap<String, Object> getSortMap(Map<String, Object> map) {
        String[] keys = map.keySet().toArray(new String[0]);
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        Arrays.sort(keys);
        for (String key : keys) {
            if (map.get(key) == null || map.get(key) instanceof JSONArray || map.get(key) instanceof JSONObject) {
                continue;
            }
            sortedMap.put(key, map.get(key));
        }
        return sortedMap;
    }

}
