package com.cuukenn.common.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import protocol.Message;

/**
 * @author changgg
 */
@Slf4j
@RequiredArgsConstructor
public class TransportProtocolInvocation extends SimpleChannelInboundHandler<Message.TransportProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.TransportProtocol msg) throws Exception {
        ProtocolFactory.handle(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("exceptionCaught", cause);
        super.exceptionCaught(ctx, cause);
    }
}
