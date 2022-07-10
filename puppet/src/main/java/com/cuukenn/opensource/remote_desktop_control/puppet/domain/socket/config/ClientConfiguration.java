package com.cuukenn.opensource.remote_desktop_control.puppet.domain.socket.config;

import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.ClientChannelInitializer;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.TCPNetworkClient;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.socket.bound.PuppetIdRegisterHandler;
import com.cuukenn.opensource.remote_desktop_control.puppet.interfaces.inbound.invocation.KeyBoardControlInvocation;
import com.cuukenn.opensource.remote_desktop_control.puppet.interfaces.inbound.invocation.MouseControlInvocation;
import com.cuukenn.opensource.remote_desktop_control.puppet.interfaces.inbound.invocation.StartControlInvocation;
import com.cuukenn.opensource.remote_desktop_control.puppet.interfaces.inbound.invocation.TerminalControlInvocation;
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
    @ConfigurationProperties(prefix = "app.puppet")
    public NetworkProperties tcpProperties() {
        return new NetworkProperties();
    }

    @Bean
    public TCPNetworkClient nettyClient(NetworkProperties properties) {
        return new TCPNetworkClient(properties) {
            @Override
            protected ChannelHandler getChannelHandler0() {
                return new ClientChannelInitializer(properties, this) {
                    @Override
                    protected void initChannel2(NioSocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new PuppetIdRegisterHandler((NetworkProperties) properties));
                        super.initChannel2(socketChannel);
                        socketChannel.pipeline()
                                .addLast(new StartControlInvocation())
                                .addLast(new TerminalControlInvocation())
                                .addLast(new KeyBoardControlInvocation())
                                .addLast(new MouseControlInvocation());
                    }
                };
            }
        };
    }
}
