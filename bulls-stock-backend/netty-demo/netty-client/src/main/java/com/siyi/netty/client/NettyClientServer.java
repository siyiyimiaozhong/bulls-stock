package com.siyi.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-14 00:38
 * @Description:
 */
public class NettyClientServer {
    /**
     * 服务端的ip
     */
    private String ip;
    /**
     * 服务端的端口
     */
    private int port;

    public NettyClientServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void runServer() throws Exception {
        // 1. 定义Boss监听组
        EventLoopGroup bossGroup  = new NioEventLoopGroup();
        // 2. 定义服务启动类
        Bootstrap bootstrap = new Bootstrap();
        // 3. 配置服务的启动参数
        bootstrap.group(bossGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //管道注册handler
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        //编码通道处理
                        pipeline.addLast("decode", new StringDecoder());
                        //转码通道处理
                        pipeline.addLast("encode", new StringEncoder());

                        // 处理来自服务端的响应信息
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        System.out.println("-------client 启动------");
        // 4. 启动客户端
        ChannelFuture cf = bootstrap.connect(ip, port).sync();

        String reqStr = "客户端发起连接请求";

        Channel channel = cf.channel();
        // 5. 客户端发送数据
        channel.writeAndFlush(reqStr);
        // 6. 通过监听线程，将输入的信息发送至服务端
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                        String msg = in.readLine();
                        channel.writeAndFlush(msg);
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws Exception {
        new NettyClientServer("127.0.0.1", 9911).runServer();
    }
}
