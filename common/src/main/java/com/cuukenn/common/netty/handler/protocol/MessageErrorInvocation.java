package com.cuukenn.common.netty.handler.protocol;

import com.cuukenn.common.netty.util.UIUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import protocol.Message;

/**
 * 服务端相应ping请求
 *
 * @author changgg
 */
@Slf4j
public class MessageErrorInvocation extends ErrorInvocation {
    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.ERROR;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        super.invoke(ctx, message);
        UIUtil.errorMessage(message.getError().getCode(), message.getError().getMessage());
    }
}
