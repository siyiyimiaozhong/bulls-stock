package com.siyi.trade.grpc.server.service;

import com.siyi.grpc.lib.StockServiceGrpc;
import com.siyi.grpc.lib.StockServiceReply;
import com.siyi.grpc.lib.StockServiceRequest;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Random;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-12 19:57
 * @Description:
 */
@GrpcService
public class GrpcStockService extends StockServiceGrpc.StockServiceImplBase {


    /**
     * 获取股票价格接口
     * @param request
     * @param responseObserver
     */
    @Override
    public void getStockPrice(StockServiceRequest request, StreamObserver<StockServiceReply> responseObserver) {
        String msg = "股票名称： " + request.getName() + ", 股票价格：" + (new Random().nextInt(100 - 20) + 20);
        StockServiceReply reply = StockServiceReply.newBuilder().setMessage(msg).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}