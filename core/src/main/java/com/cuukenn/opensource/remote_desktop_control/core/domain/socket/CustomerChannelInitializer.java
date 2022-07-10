package com.cuukenn.opensource.remote_desktop_control.core.domain.socket;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.codec.PacketCodec;
import com.cuukenn.opensource.remote_desktop_control.core.interfaces.inbound.invocation.ErrorInvocation;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.RequiredArgsConstructor;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class CustomerChannelInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel socketChannel) throws Exception {
        initChannel0(socketChannel);
        initChannel1(socketChannel);
        initChannel2(socketChannel);
    }

    protected void initChannel0(NioSocketChannel socketChannel) {

    }

    protected void initChannel1(NioSocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new PacketCodec());
    }

    protected void initChannel2(NioSocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new ErrorInvocation());
    }
}
