package com.cuukenn.client.netty.config;

import com.cuukenn.common.netty.client.config.BaseNettyClientProperties;
import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.common.netty.client.handler.protocol.PongInvocation;
import com.cuukenn.common.netty.handler.protocol.ErrorInvocation;
import com.cuukenn.common.netty.handler.protocol.MessageErrorInvocation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
public class NettyConfiguration {
    @Bean
    public PongInvocation pongInvocation() {
        return new PongInvocation();
    }

    @Bean
    public ErrorInvocation errorInvocation() {
        return new MessageErrorInvocation();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.client")
    public BaseNettyClientProperties tcpProperties() {
        return new NettyProperties();
    }

    @Bean
    public NettyClient nettyClient(BaseNettyClientProperties properties) {
        return new NettyClient(properties);
    }
}
