package com.cuukenn.opensource.remote_desktop_control.core.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ErrorPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class ErrorInvocation extends SimpleChannelInboundHandler<ErrorPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ErrorPacket message) throws Exception {
        log.error("some error happened,message:[{}]", message);
    }
}
