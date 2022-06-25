package com.cuukenn.common.netty.client.handler;

import com.cuukenn.common.netty.client.config.BaseNettyClientProperties;
import com.cuukenn.common.netty.client.handler.bound.ConnectionStateWatchDog;
import com.cuukenn.common.netty.handler.CustomerChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class ClientChannelInitializer extends CustomerChannelInitializer {
    private final BaseNettyClientProperties properties;
    protected final NettyClient nettyClient;

    @Override
    protected void initChannel0(NioSocketChannel socketChannel) {
        socketChannel.pipeline()
                .addLast(
                        new IdleStateHandler(
                                0,
                                properties.getWriterIdleTimeSeconds(),
                                0,
                                TimeUnit.SECONDS)
                );
    }

    protected void initChannel2(NioSocketChannel socketChannel) {
        super.initChannel2(socketChannel);
        socketChannel.pipeline().addLast(new ConnectionStateWatchDog(nettyClient::reconnect,properties.getApplicationType()));
    }
}
