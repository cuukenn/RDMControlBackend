package com.cuukenn.puppet.netty.handler.bound;

import com.cuukenn.puppet.netty.config.NettyProperties;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.RequiredArgsConstructor;
import protocol.Message;

/**
 * @author changgg
 */
@RequiredArgsConstructor
public class PuppetNameRegisterTrigger extends ChannelOutboundHandlerAdapter {
    private final NettyProperties properties;

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Message.TransportProtocol) {
            msg = ((Message.TransportProtocol) msg).toBuilder().setPuppetName(properties.getPuppetName()).build();
        }
        super.write(ctx, msg, promise);
    }
}
