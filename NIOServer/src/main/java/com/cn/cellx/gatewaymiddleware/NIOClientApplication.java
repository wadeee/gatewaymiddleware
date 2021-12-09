package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.NIOClient.NIOClient;
import com.cn.cellx.gatewaymiddleware.utils.spring.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication(scanBasePackages = {"com.cn.cellx.gatewaymiddleware"})
public class NIOClientApplication {

    private static NIOClient nioClientStatic;

    @PostConstruct
    public void initialize() {
        nioClientStatic = SpringUtils.getBean(NIOClient.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(NIOClientApplication.class, args);

        nioClientStatic.start();
    }

}
