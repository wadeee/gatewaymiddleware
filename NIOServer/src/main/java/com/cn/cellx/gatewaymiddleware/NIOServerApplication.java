package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.NIOServer.NIOServer;
import com.cn.cellx.gatewaymiddleware.utils.spring.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class NIOServerApplication {

    private static NIOServer nioServerStatic;

    @PostConstruct
    public void initialize() {
        nioServerStatic = SpringUtils.getBean(NIOServer.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(NIOServerApplication.class, args);

        nioServerStatic.start();
    }

}
