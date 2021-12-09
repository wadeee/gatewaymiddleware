package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.UDPClient.UDPClient;
import com.cn.cellx.gatewaymiddleware.utils.spring.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UDPClientApplication {

    private static UDPClient udpClientStatic;

    @PostConstruct
    public void initialize() {
        udpClientStatic = SpringUtils.getBean(UDPClient.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UDPClientApplication.class, args);

        udpClientStatic.start();
    }

}
