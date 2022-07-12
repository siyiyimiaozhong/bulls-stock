package com.siyi.trade.grpc.client.service;

import com.siyi.grpc.lib.StockServiceGrpc;
import com.siyi.grpc.lib.StockServiceReply;
import com.siyi.grpc.lib.StockServiceRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-12 16:21
 * @Description:
 */
@Service
public class GrpcClientService {

    @GrpcClient("grpc-server")
    private StockServiceGrpc.StockServiceBlockingStub stockServiceBlockingStub;

    /**
     * 获取股票价格接口
     * @param name
     * @return
     */
    public String getStockPrice(final String name) {

        try {
            // 1.创建请求参数结构体
            StockServiceRequest request = StockServiceRequest.newBuilder().setName(name).build();
            // 2. 进行远程访问调用
            final StockServiceReply response = stockServiceBlockingStub.getStockPrice(request);
            // 3. 返回消息
            return response.getMessage();
        }catch(Exception e) {
            return "error!";
        }
    }
}
