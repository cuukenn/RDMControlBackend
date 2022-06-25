package com.cuukenn.server.netty.handler.protocol;

import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import com.cuukenn.common.netty.util.ProtocolUtil;
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
    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.HEART_BEAT;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        ctx.channel().writeAndFlush(ProtocolUtil.empty(ApplicationType.SERVER, Message.ProtocolType.HEART_BEAT));
    }
}
