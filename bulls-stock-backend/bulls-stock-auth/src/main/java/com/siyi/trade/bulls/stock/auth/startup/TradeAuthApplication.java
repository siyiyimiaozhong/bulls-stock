package com.siyi.trade.bulls.stock.auth.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: siyiyimiaozhong
 * @Description:
 * @Project: bulls-stock-backend
 * @Package: com.siyi.trade.bulls.stock.auth.startup
 * @ClassName: TradeAuthApplication.java
 * @CreateTime: 2022-11-13  23:41
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.siyi"})
@EnableCaching
@MapperScan(basePackages = {"com.siyi.trade.bulls.stock.auth.mapper"})
public class TradeAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeAuthApplication.class, args);
    }
}
