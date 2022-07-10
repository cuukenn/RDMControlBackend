package com.cuukenn.opensource.remote_desktop_control.server.domain.socket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
public class ServerConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "app.server")
    public ServerNetworkProperties tcpProperties() {
        return new ServerNetworkProperties();
    }
}
