package com.cuukenn.common.netty.client.handler.bound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import protocol.Message;

/**
 * 写超时后发送ping请求维持连接
 * 连接断开重新连接
 *
 * @author changgg
 */
@Slf4j
public class ConnectorStateWatchDog extends ChannelInboundHandlerAdapter {
    private static final Message.TransportProtocol PING = Message.TransportProtocol.newBuilder()
            .setType(Message.ProtocolType.PING_REQUEST).setNullValue(Message.NullValue.NULL_VALUE)
            .build();
    private final Runnable channelInactiveAction;

    public ConnectorStateWatchDog(Runnable channelInactiveAction) {
        this.channelInactiveAction = channelInactiveAction;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            IdleState state = stateEvent.state();
            if (state == IdleState.WRITER_IDLE) {
                log.debug("long time no write,send ping request to server");
                ctx.writeAndFlush(PING);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.channelInactiveAction.run();
        super.channelInactive(ctx);
    }
}
