package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.NIOClient.NIOClient;
import com.cn.cellx.gatewaymiddleware.NIOServer.NIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

@SpringBootApplication
public class GatewayMiddlewareTestApplication {

    @Autowired
    private NIOClient nioClient;

    private static NIOClient nioClientStatic;

    @PostConstruct
    public void initialize() {
        nioClientStatic = nioClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayMiddlewareTestApplication.class, args);

        nioClientStatic.start();
    }

}
