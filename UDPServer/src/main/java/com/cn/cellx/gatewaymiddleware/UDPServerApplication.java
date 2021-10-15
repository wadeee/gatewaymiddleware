package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.UDPServer.UDPServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UDPServerApplication {

    @Autowired
    private UDPServer udpServer;

    private static UDPServer udpServerStatic;

    @PostConstruct
    public void initialize() {
        UDPServerApplication.udpServerStatic = udpServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(UDPServerApplication.class, args);

        udpServerStatic.start();
    }

}
