package com.cuukenn.puppet.netty.config;

import com.cuukenn.common.netty.client.config.BaseNettyClientProperties;
import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.common.netty.client.handler.protocol.PongInvocation;
import com.cuukenn.common.netty.protocol.IProtocolInvocation;
import com.cuukenn.common.netty.protocol.ProtocolFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author changgg
 */
@Configuration
public class NettyConfiguration implements InitializingBean {
    private List<IProtocolInvocation> protocolHandlers;

    @Autowired
    public void setProtocolHandlers(List<IProtocolInvocation> protocolHandlers) {
        this.protocolHandlers = protocolHandlers;
    }

    @Bean
    @ConfigurationProperties(prefix = "app.puppet")
    public BaseNettyClientProperties tcpProperties() {
        return new NettyProperties();
    }

    @Bean
    public PongInvocation pongHandler() {
        return new PongInvocation();
    }

    @Bean
    public NettyClient nettyClient(BaseNettyClientProperties properties) {
        return new NettyClient(properties);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        protocolHandlers.forEach(ProtocolFactory::addHandlers);
    }
}
