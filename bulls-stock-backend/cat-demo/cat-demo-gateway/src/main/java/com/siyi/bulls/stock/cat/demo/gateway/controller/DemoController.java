package com.siyi.bulls.stock.cat.demo.gateway.controller;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-06-06 21:44
 * @Description: demo控制器
 */
@RestController
public class DemoController {
    private final RestTemplate restTemplate;
    @Value("${service2.address:localhost:8082}")
    private String serviceAddress;

    public DemoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 模拟正常的情况
     * @return
     * @throws Exception
     */
    @RequestMapping("/gateway")
    public String gateway() throws Exception {
        Thread.sleep(100);
        String response = this.restTemplate.getForObject("http://" + this.serviceAddress + "/order", String.class);
        return "gateway response ==> " + response;
    }

    /**
     * 模拟一个请求异常
     * @return
     * @throws Exception
     */
    @RequestMapping("/timeout")
    public String timeout() throws Exception {
        Transaction t = Cat.newTransaction(CatConstants.TYPE_URL, "timeout");
        try {
            Thread.sleep(100);
            return this.restTemplate.getForObject("http://" + this.serviceAddress + "/timeout", String.class);
        } catch (Exception e) {
            Cat.getProducer().logError(e);
            t.setStatus(e);
            throw e;
        } finally {
            t.complete();
        }
    }
}
