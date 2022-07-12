package com.siyi.trade.grpc.client.controller;

import com.siyi.trade.grpc.client.service.GrpcClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-12 16:24
 * @Description:
 */
@RestController
public class GrpcClientController {
    private final GrpcClientService grpcClientService;

    public GrpcClientController(GrpcClientService grpcClientService) {
        this.grpcClientService = grpcClientService;
    }

    /**
     * 获取股票价格接口
     *
     * @param name
     * @return
     */
    @RequestMapping("/getStockPrice")
    public String getStockPrice(@RequestParam(defaultValue = "中国平安") String name) {
        return grpcClientService.getStockPrice(name);
    }
}
