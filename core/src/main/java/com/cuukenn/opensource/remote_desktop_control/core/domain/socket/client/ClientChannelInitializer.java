package com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client;

import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.bound.ConnectionStateWatchDog;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.config.BaseNetworkClientProperties;
import com.cuukenn.opensource.remote_desktop_control.core.interfaces.inbound.invocation.PongInvocation;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.CustomerChannelInitializer;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.INetworkBootService;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class ClientChannelInitializer extends CustomerChannelInitializer {
    private final BaseNetworkClientProperties properties;
    protected final INetworkBootService connectService;

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
        socketChannel.pipeline()
                .addLast(new ConnectionStateWatchDog(connectService::reconnect, properties.getApplicationType()))
                .addLast(new PongInvocation());
    }
}
