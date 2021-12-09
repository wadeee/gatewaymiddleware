package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.UDPServer.UDPServer;
import com.cn.cellx.gatewaymiddleware.utils.spring.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UDPServerApplication {

    private static UDPServer udpServerStatic;

    @PostConstruct
    public void initialize() {
        udpServerStatic = SpringUtils.getBean(UDPServer.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(UDPServerApplication.class, args);

        udpServerStatic.start();
    }

}
