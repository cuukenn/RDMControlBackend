package com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client;

import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.AbstractNetworkBootService;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.config.BaseNetworkClientProperties;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class TCPNetworkClient extends AbstractNetworkBootService {
    public TCPNetworkClient(BaseNetworkClientProperties properties) {
        super(properties);
    }

    @Override
    protected Bootstrap initBootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .remoteAddress(properties.getServerAddress(), properties.getPort())
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true);
        return bootstrap;
    }
}
