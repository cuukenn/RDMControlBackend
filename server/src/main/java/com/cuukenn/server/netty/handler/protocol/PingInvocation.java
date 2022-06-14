package com.cuukenn.server.netty.handler.protocol;

import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;
import protocol.Message;

/**
 * 服务端相应ping请求
 *
 * @author changgg
 */
@Service
public class PingInvocation implements ITransportProtocolInvocation {
    private static final Message.TransportProtocol PONG = Message.TransportProtocol.newBuilder()
            .setType(Message.ProtocolType.PONG_RESPONSE).setNullValue(Message.NullValue.NULL_VALUE)
            .build();

    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.PING_REQUEST;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        ctx.channel().writeAndFlush(PONG);
    }
}
