package com.cuukenn.common.netty.client.handler.bound;

import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.util.ProtocolUtil;
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
    private final Runnable channelInactiveAction;
    private final ApplicationType type;

    public ConnectorStateWatchDog(Runnable channelInactiveAction, ApplicationType type) {
        this.channelInactiveAction = channelInactiveAction;
        this.type = type;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent stateEvent = (IdleStateEvent) evt;
            IdleState state = stateEvent.state();
            if (state == IdleState.WRITER_IDLE) {
                log.debug("long time no write,send ping request to server");
                ctx.writeAndFlush(ProtocolUtil.createProtocol(type)
                        .setType(Message.ProtocolType.PING).setNullValue(Message.NullValue.NULL_VALUE)
                        .build());
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
