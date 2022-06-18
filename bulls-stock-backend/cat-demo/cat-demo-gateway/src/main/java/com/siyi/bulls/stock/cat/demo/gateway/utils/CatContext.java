package com.siyi.bulls.stock.cat.demo.gateway.utils;

import com.dianping.cat.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-07 00:25
 * @Description:
 */
public class CatContext implements Cat.Context{
    /**
     * 存储链路监控相关信息
     */
    private Map<String, String> properties = new HashMap<>();


    @Override
    public void addProperty(String key, String value) {

    }

    @Override
    public String getProperty(String key) {
        return null;
    }
}
