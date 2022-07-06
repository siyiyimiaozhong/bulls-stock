package com.siyi.nacos.discovery.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-06 07:33
 * @Description:
 */
@RestController
public class DemoController {

    /**
     * 查询股票架构的接口
     * @param name
     * @return
     */
    @GetMapping("/getStockPrice")
    public String getStockPrice(@RequestParam(defaultValue = "中国平安") String name) {
        return "股票名称：" + name + ", 股票价格：" + (new Random().nextInt(100 - 20) + 20);
    }
}
