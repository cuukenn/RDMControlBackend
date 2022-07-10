package com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.DisconnectPacket;
import com.cuukenn.opensource.remote_desktop_control.server.infrastructure.util.ChannelHolderUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changgg
 */
public class TerminalControlInvocation extends SimpleChannelInboundHandler<DisconnectPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DisconnectPacket msg) throws Exception {
        ctx.writeAndFlush(msg);
        ChannelHolderUtil.getAnotherPair(ctx.channel()).ifPresent(channel -> channel.writeAndFlush(msg));
        ChannelHolderUtil.unbind(ctx.channel());
    }
}
