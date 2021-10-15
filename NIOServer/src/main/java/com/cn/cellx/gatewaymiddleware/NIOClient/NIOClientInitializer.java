package com.cn.cellx.gatewaymiddleware.NIOClient;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Copyright (C) 2021 CellX  All Rights Reserved
 * This software and code can be freely used for study and research.
 * For commercial purposes, please contact the owner for permission.
 *
 * @ClassName NettyClientInitializer
 * @Description TODO
 * @Author wade
 * @Date 2021/10/14 11:25
 * @Version 1.0
 */
public class NIOClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("decoder", new StringDecoder());
        socketChannel.pipeline().addLast("encoder", new StringEncoder());
        socketChannel.pipeline().addLast(new NIOClientHandler());
    }
}
