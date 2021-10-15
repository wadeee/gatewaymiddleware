package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.NIOServer.NIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NIOServerApplication {

    @Autowired
    private NIOServer nioServer;

    private static NIOServer nioServerStatic;

    @PostConstruct
    public void initialize() {
        NIOServerApplication.nioServerStatic = nioServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(NIOServerApplication.class, args);

        nioServerStatic.start();
    }

}
