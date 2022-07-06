package com.siyi.nacos.discovery.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-06 07:25
 * @Description:
 */
@RestController
public class DemoController {

    private final RestTemplate restTemplate;


    public DemoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 提供客户端获取股票价格接口
     * @param name
     * @return
     */
    @GetMapping("/clientGetStockPrice")
    public String clientGetStockPrice(@RequestParam String name) {
        return this.restTemplate.getForObject("http://nacos-discovery-server/getStockPrice?name=" + name, String.class);
    }
}
