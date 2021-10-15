package com.cn.cellx.gatewaymiddleware.UDPServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * Copyright (C) 2021 CellX  All Rights Reserved
 * This software and code can be freely used for study and research.
 * For commercial purposes, please contact the owner for permission.
 *
 * @ClassName ServerChannelInitializer
 * @Description TODO
 * @Author wade
 * @Date 2021/10/14 10:57
 * @Version 1.0
 */
public class UDPServerInitializer extends ChannelInitializer<NioDatagramChannel> {
    @Override
    protected void initChannel(NioDatagramChannel nioDatagramChannel) {
        ChannelPipeline pipeline = nioDatagramChannel.pipeline();
        //添加编解码
        pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new UDPServerHandler());
    }
}
