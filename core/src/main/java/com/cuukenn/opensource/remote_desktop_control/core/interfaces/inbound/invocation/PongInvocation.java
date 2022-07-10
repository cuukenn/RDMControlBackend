package com.cuukenn.opensource.remote_desktop_control.core.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.PongPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class PongInvocation extends SimpleChannelInboundHandler<PongPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PongPacket msg) throws Exception {
        log.debug("receive pong response,message:[{}]", msg);
    }
}
