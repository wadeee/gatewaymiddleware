package com.cn.cellx.gatewaymiddleware.NIOServer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Copyright (C) 2021 CellX  All Rights Reserved
 * This software and code can be freely used for study and research.
 * For commercial purposes, please contact the owner for permission.
 *
 * @ClassName SocketConfig
 * @Description TODO
 * @Author wade
 * @Date 2021/10/12 14:40
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "nio-server")
@Data
public class NIOServerConfig {
    private String host;
    private Integer port;
}
