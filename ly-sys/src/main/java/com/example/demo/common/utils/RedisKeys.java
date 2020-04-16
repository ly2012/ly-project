package com.example.demo.common.utils;

/**
 * Redis所有Keys
 *
 * @author
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
