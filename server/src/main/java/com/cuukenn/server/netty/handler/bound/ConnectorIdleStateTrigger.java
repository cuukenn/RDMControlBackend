package com.cuukenn.server.netty.handler.bound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 在规定时间内未收到客户端的任何数据包, 将主动断开该连接
 *
 * @author changgg
 */
@Slf4j
public class ConnectorIdleStateTrigger extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                //在规定时间内没有收到客户端的上行数据, 主动断开连接
                log.debug("stop the idle connection with long no request,ctx:[{}]", ctx);
                ctx.disconnect();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
