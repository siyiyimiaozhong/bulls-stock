package com.siyi.bulls.stock.cat.demo.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-07 21:58
 * @Description:
 */
@RestController
public class AccountDemoController {

    /**
     * 提供账户服务接口
     * @return
     * @throws Exception
     */
    @RequestMapping("/account")
    public String account() throws Exception {
        Thread.sleep(150);
        return "account success";
    }
}
