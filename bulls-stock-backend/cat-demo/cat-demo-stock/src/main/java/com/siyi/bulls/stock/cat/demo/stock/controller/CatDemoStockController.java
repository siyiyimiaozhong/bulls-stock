package com.siyi.bulls.stock.cat.demo.stock.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-12 17:04
 * @Description:
 */
@RestController
public class CatDemoStockController {

    /**
     * 提供库存接口
     * @return
     * @throws Exception
     */
    @RequestMapping("/stock")
    public String service4MethodInController() throws Exception {
        Thread.sleep(200);
        return "stock success";
    }
}
