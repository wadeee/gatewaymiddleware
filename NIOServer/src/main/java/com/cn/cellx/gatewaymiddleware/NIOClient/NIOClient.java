package com.cn.cellx.gatewaymiddleware.NIOClient;

import com.cn.cellx.gatewaymiddleware.NIOServer.NIOServerConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C) 2021 CellX  All Rights Reserved
 * This software and code can be freely used for study and research.
 * For commercial purposes, please contact the owner for permission.
 *
 * @ClassName NettyClient
 * @Description TODO
 * @Author wade
 * @Date 2021/10/14 11:25
 * @Version 1.0
 */
@Slf4j
@Service
public class NIOClient {

    @Autowired
    private NIOServerConfig nioServerConfig;

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap()
                .group(group)
                //该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输
                .option(ChannelOption.TCP_NODELAY, true)
                .channel(NioSocketChannel.class)
                .handler(new NIOClientInitializer());

        try {
            ChannelFuture future = bootstrap.connect(nioServerConfig.getHost(), nioServerConfig.getPort()).sync();
            log.info("客户端成功....");
            //发送消息
            future.channel().writeAndFlush("你好啊");
            future.channel().writeAndFlush("我给你发啥你就给我发啥");
            // 等待连接被关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }
}
