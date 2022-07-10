package com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.server.infrastructure.util.ChannelHolderUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changgg
 */
public abstract class AbstractForwardMessageInvocation<T> extends SimpleChannelInboundHandler<T> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        ChannelHolderUtil.getAnotherPair(ctx.channel()).ifPresent(channel -> channel.writeAndFlush(msg));
    }
}
