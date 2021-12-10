package com.cn.cellx.gatewaymiddleware.UDPClient;

import com.cn.cellx.gatewaymiddleware.utils.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 客户端业务处理
 *
 * @author LionLi
 */
@Slf4j
public class UDPClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("客户端Active .....");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        System.out.println(StringUtils.format("Data: {}; IP: {}; port: {};", packet.content().toString(StandardCharsets.UTF_8), packet.sender().getHostString(), packet.sender().getPort()));
        System.out.println("请输入发送内容：");
        Scanner input=new Scanner(System.in);
        String str=input.next();
        if ("exit".equals(str)) {
            ctx.close();
            return;
        }
        ByteBuf byteBuf = Unpooled.copiedBuffer(str.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(new DatagramPacket(byteBuf, packet.sender()));
    }

}
