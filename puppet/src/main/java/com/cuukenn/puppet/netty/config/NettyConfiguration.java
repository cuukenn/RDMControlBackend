package com.cuukenn.puppet.netty.config;

import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.common.netty.client.handler.NettyClientChannelInitializer;
import com.cuukenn.common.netty.client.handler.protocol.PongInvocation;
import com.cuukenn.common.netty.client.ui.StageController;
import com.cuukenn.puppet.netty.handler.bound.PuppetNameRegisterTrigger;
import io.netty.channel.ChannelHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
public class NettyConfiguration {
    @Bean
    public StageController stageController() {
        return new StageController();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.puppet")
    public NettyProperties tcpProperties() {
        return new NettyProperties();
    }

    @Bean
    public PongInvocation pongHandler() {
        return new PongInvocation();
    }

    @Bean
    public NettyClient nettyClient(NettyProperties properties) {
        return new NettyClient(properties) {
            @Override
            protected ChannelHandler getChannelHandler0() {
                return new NettyClientChannelInitializer(properties, this) {
                    @Override
                    protected void initChannel2(NioSocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new PuppetNameRegisterTrigger(properties));
                        super.initChannel2(socketChannel);
                    }
                };
            }
        };
    }
}
