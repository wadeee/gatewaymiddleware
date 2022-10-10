package com.cn.cellx.gatewaymiddleware.UDPClient;

import com.cn.cellx.gatewaymiddleware.utils.DataGramUtils;
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
        ByteBuf buf = packet.content();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println(StringUtils.format("Data: {}; IP: {}; port: {};", DataGramUtils.transHexString(bytes), packet.sender().getHostString(), packet.sender().getPort()));
        System.out.println("请输入发送内容：");
        Scanner input=new Scanner(System.in);
        String str=input.next();
//        if ("exit".equals(str)) {
//            ctx.close();
//            return;
//        }
//        ByteBuf byteBuf = Unpooled.copiedBuffer(str.getBytes(StandardCharsets.UTF_8));
        ByteBuf byteBuf = Unpooled.copiedBuffer(DataGramUtils.transHexStringToBytes("235C00000001100101F4D5FD060000CC4061C285162C27B09E7D7AE9D3AF4F9E3DFAEB83574F9E3D7DE9D3A70FBE7DFAEBD7AF4F9E3D7BEDDBB76FDE3D7AE9DFBF7FFEFDFBE9D3A70F1E3C78E1C3A74F9EC64F00002A"));
        ctx.writeAndFlush(new DatagramPacket(byteBuf, packet.sender()));
    }

}
