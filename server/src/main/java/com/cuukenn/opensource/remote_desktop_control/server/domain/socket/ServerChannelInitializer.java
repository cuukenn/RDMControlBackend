package com.cuukenn.opensource.remote_desktop_control.server.domain.socket;

import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.CustomerChannelInitializer;
import com.cuukenn.opensource.remote_desktop_control.server.domain.socket.bound.ChannelHolderHandler;
import com.cuukenn.opensource.remote_desktop_control.server.domain.socket.bound.ConnectorIdleStateTrigger;
import com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation.ConnectInvocation;
import com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation.KeyBoardControlInvocation;
import com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation.MouseControlInvocation;
import com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation.PingInvocation;
import com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation.ScreenInvocation;
import com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation.TerminalControlInvocation;
import com.cuukenn.opensource.remote_desktop_control.server.domain.socket.config.ServerNetworkProperties;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class ServerChannelInitializer extends CustomerChannelInitializer {
    private final ServerNetworkProperties properties;

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
        socketChannel.pipeline()
                .addLast(new ChannelHolderHandler())
                .addLast(new PingInvocation())
                .addLast(new ConnectInvocation())
                .addLast(new TerminalControlInvocation())
                .addLast(new KeyBoardControlInvocation())
                .addLast(new MouseControlInvocation())
                .addLast(new ScreenInvocation());
        super.initChannel2(socketChannel);
    }
}
