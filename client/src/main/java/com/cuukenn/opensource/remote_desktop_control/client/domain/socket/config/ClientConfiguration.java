package com.cuukenn.opensource.remote_desktop_control.client.domain.socket.config;

import com.cuukenn.opensource.remote_desktop_control.client.interfaces.inbound.invocation.ConnectedInvocation;
import com.cuukenn.opensource.remote_desktop_control.client.interfaces.inbound.invocation.ScreenUpdateInvocation;
import com.cuukenn.opensource.remote_desktop_control.client.interfaces.inbound.invocation.TerminalControlInvocation;
import com.cuukenn.opensource.remote_desktop_control.core.interfaces.inbound.invocation.ErrorInvocation;
import com.cuukenn.opensource.remote_desktop_control.core.interfaces.inbound.invocation.MessageErrorInvocation;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.bound.IdInfoRegisterHandler;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.ClientChannelInitializer;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.TCPNetworkClient;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.config.BaseNetworkClientProperties;
import io.netty.channel.ChannelHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
public class ClientConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "app.client")
    public BaseNetworkClientProperties tcpProperties() {
        return new NetworkProperties();
    }

    @Bean
    public TCPNetworkClient nettyClient(BaseNetworkClientProperties properties) {
        return new TCPNetworkClient(properties) {
            @Override
            protected ChannelHandler getChannelHandler0() {
                return new ClientChannelInitializer(properties, this) {
                    @Override
                    protected void initChannel2(NioSocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new IdInfoRegisterHandler(properties));
                        super.initChannel2(socketChannel);
                        socketChannel.pipeline().remove(ErrorInvocation.class);
                        socketChannel.pipeline()
                                .addLast(new MessageErrorInvocation())
                                .addLast(new ConnectedInvocation())
                                .addLast(new ScreenUpdateInvocation())
                                .addLast(new TerminalControlInvocation());
                    }
                };
            }
        };
    }
}
