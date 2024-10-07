package com.chengfeng.study.myspringbootproject.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * WebSocketConfig class
 *
 * @author chengfeng
 * @date 2021/2/28 /0028 19:04
 */
@Component
public class WebSocketConfig {
    private static final Logger log = Logger.getLogger(WebSocketConfig.class.getName());

    /**
     * ServerEndpointExporter 作用
     * 这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        log.log(Level.INFO, "开始加载WebSocket...");
        return new ServerEndpointExporter();
    }

}
