package com.cuukenn.common.netty.client.handler.protocol;

import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import protocol.Message;

/**
 * @author changgg
 */
@Slf4j
public class PongInvocation implements ITransportProtocolInvocation {
    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.PONG_RESPONSE;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        log.debug("receive pong response,message:[{}]", message);
    }
}
