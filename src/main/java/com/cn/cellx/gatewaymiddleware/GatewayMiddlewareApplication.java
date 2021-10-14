package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.NIOServer.NIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GatewayMiddlewareApplication {

    @Autowired
    private NIOServer nioServer;

    private static NIOServer nioServerStatic;

    @PostConstruct
    public void initialize() {
        GatewayMiddlewareApplication.nioServerStatic = nioServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayMiddlewareApplication.class, args);

        nioServerStatic.start();
    }

}
