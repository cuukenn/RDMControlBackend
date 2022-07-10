package com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.PingPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.PongPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author changgg
 */
public class PingInvocation extends SimpleChannelInboundHandler<PingPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PingPacket msg) throws Exception {
        ctx.channel().writeAndFlush(new PongPacket());
    }
}
