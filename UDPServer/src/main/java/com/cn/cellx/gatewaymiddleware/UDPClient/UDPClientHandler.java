package com.cn.cellx.gatewaymiddleware.UDPClient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * 客户端业务处理
 *
 * @author LionLi
 */
@Slf4j
public class UDPClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        System.out.println("客户端接收到消息：" + packet.content().toString(StandardCharsets.UTF_8));
//        ctx.close();
        // 向客户端发送消息
        ByteBuf byteBuf = Unpooled.copiedBuffer("你好服务器".getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(new DatagramPacket(byteBuf, packet.sender()));
    }

}
