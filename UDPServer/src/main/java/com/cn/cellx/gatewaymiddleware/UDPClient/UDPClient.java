package com.cn.cellx.gatewaymiddleware.UDPClient;

import com.cn.cellx.gatewaymiddleware.UDPServer.UDPServerConfig;
import com.cn.cellx.gatewaymiddleware.utils.DataGramUtils;
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
            Channel channel = bootstrap.bind(0).sync().channel();
            InetSocketAddress address = new InetSocketAddress(udpServerConfig.getHost(), udpServerConfig.getPort());
//            ByteBuf byteBuf = Unpooled.copiedBuffer("你好服务器".getBytes(StandardCharsets.UTF_8));
            ByteBuf byteBuf = Unpooled.copiedBuffer(DataGramUtils.transHexStringToBytes("235C00000001100101F4D5FD060000CC4061C285162C27B09E7D7AE9D3AF4F9E3DFAEB83574F9E3D7DE9D3A70FBE7DFAEBD7AF4F9E3D7BEDDBB76FDE3D7AE9DFBF7FFEFDFBE9D3A70F1E3C78E1C3A74F9EC64F00002A"));
            channel.writeAndFlush(new DatagramPacket(byteBuf, address)).sync();
            channel.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
