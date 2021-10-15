package com.cn.cellx.gatewaymiddleware;

import com.cn.cellx.gatewaymiddleware.UDPClient.UDPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UDPClientApplication {

    @Autowired
    private UDPClient udpClient;

    private static UDPClient udpClientStatic;

    @PostConstruct
    public void initialize() {
        udpClientStatic = udpClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(UDPClientApplication.class, args);

        udpClientStatic.start();
    }

}
