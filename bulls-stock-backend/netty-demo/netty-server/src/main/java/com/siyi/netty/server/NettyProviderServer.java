package com.siyi.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-13 21:52
 * @Description:
 */
public class NettyProviderServer {

    private int port;

    public NettyProviderServer(int port) {
        this.port = port;
    }

    /**
     * Netty通讯服务端的启动实现
     *
     * @throws Exception
     */
    public void runServer() throws Exception {
        // 1. 定义时间的BOSS监听组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 2. 定义Worker, 用于处理已经被接收的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 4. 定义NIO的服务启动类
            ServerBootstrap sbs = new ServerBootstrap();
            // 5. 配置NIO服务的启动相关参数
            sbs.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 设置tcp最大缓存链接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 保持链接的正常状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 管道的注册handler
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 编码通道处理
                            pipeline.addLast("decode", new StringDecoder());
                            // 转码通道处理
                            pipeline.addLast("encode", new StringEncoder());
                            // 处理接收到的请求数据
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });

            System.err.println("-------server 启动------");

            // 监听输入端消息并发送给所有客户端
            new Thread(() -> {
                try {
                    while (true) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                        String str = in.readLine();
                        if (NettyServerHandler.channelList.size() > 0) {
                            for (Channel channel : NettyServerHandler.channelList) {
                                channel.writeAndFlush(str);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // 绑定端口，开始接受链接
            ChannelFuture cf = sbs.bind(port).sync();
            cf.channel().closeFuture().sync();

        } finally {
            // 实现Netty的优雅关闭
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyProviderServer(9911).runServer();
    }
}
