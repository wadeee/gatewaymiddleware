package com.cn.cellx.gatewaymiddleware.UDPClient;

import com.cn.cellx.gatewaymiddleware.UDPServer.UDPServerConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * udp客户端
 *
 * @author LionLi
 */
@Slf4j
@Service
public class UDPClient {

    @Autowired
    private UDPServerConfig udpServerConfig;

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new UDPClientInitializer());
//            for (int i = 0; i < 3; i++) {
//                Channel channel = bootstrap.bind(19345).sync().channel();
//                InetSocketAddress address = new InetSocketAddress(udpServerConfig.getHost(), udpServerConfig.getPort());
//                ByteBuf byteBuf = Unpooled.copiedBuffer("你好服务器".getBytes(StandardCharsets.UTF_8));
//                channel.writeAndFlush(new DatagramPacket(byteBuf, address)).sync();
//                channel.closeFuture().await();
//            }
            Channel channel = bootstrap.bind(19345).sync().channel();
            InetSocketAddress address = new InetSocketAddress(udpServerConfig.getHost(), udpServerConfig.getPort());
            ByteBuf byteBuf = Unpooled.copiedBuffer("你好服务器".getBytes(StandardCharsets.UTF_8));
            channel.writeAndFlush(new DatagramPacket(byteBuf, address)).sync();
            channel.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
