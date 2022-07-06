package com.siyi.nacos.config.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-06 07:07
 * @Description:
 */
@RestController
@RefreshScope
public class DemoController {

    @Value(("${stockName: 中国平安}"))
    private String stockName;

    @GetMapping("/getStockName")
    public String getStockName() {
        return "股票名称：" + this.stockName;
    }
}
