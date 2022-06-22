package com.cuukenn.server.netty;

import com.cuukenn.common.netty.handler.CustomerChannelInitializer;
import com.cuukenn.server.netty.config.ServerNettyProperties;
import com.cuukenn.server.netty.handler.bound.ConnectionHolderTrigger;
import com.cuukenn.server.netty.handler.bound.ConnectorIdleStateTrigger;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class ServerChannelInitializer extends CustomerChannelInitializer {
    private final ServerNettyProperties properties;

    @Override
    protected void initChannel0(NioSocketChannel socketChannel) {
        socketChannel.pipeline().addLast(
                new IdleStateHandler(
                        properties.getReaderIdleTimeSeconds(),
                        0,
                        0,
                        TimeUnit.SECONDS
                ))
                .addLast(new ConnectorIdleStateTrigger());
    }

    @Override
    protected void initChannel2(NioSocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new ConnectionHolderTrigger());
        super.initChannel2(socketChannel);
    }
}
