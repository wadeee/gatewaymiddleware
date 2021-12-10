package com.cn.cellx.gatewaymiddleware.UDPServer;

import com.cn.cellx.gatewaymiddleware.utils.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * 服务端业务处理
 *
 * @author LionLi
 */
@Slf4j
public class UDPServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        System.out.println(StringUtils.format("Data: {}; IP: {}; port: {};", packet.content().toString(StandardCharsets.UTF_8), packet.sender().getHostString(), packet.sender().getPort()));
        // 向客户端发送消息
        ByteBuf byteBuf = Unpooled.copiedBuffer(packet.content().toString(StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(new DatagramPacket(byteBuf, packet.sender()));
    }
}
