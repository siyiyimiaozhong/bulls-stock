package com.siyi.trade.bulls.stock.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-04 22:48
 * @Description: 测试Controller
 */
@RestController
public class TestController {
    /**
     * 提供访问测试接口， 支持POST和GET
     **/
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome bulls stock platform! !";
    }
}
