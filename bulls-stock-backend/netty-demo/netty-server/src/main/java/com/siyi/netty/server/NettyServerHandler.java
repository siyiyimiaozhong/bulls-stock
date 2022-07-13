package com.siyi.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: 敬学
 * @CreateTime: Created in 2022-07-14 00:06
 * @Description:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public static List<Channel> channelList = new LinkedList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        channelList.add(ctx.channel());
    }

    /**
     * 读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server--收到消息： " + msg);
    }


    /**
     * 出现异常的处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("server--读取数据出现异常:");
        cause.printStackTrace();
        ctx.close();
    }

}