package com.cuukenn.server.netty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
public class NettyConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "app.server")
    public ServerNettyProperties tcpProperties() {
        return new ServerNettyProperties();
    }
}
