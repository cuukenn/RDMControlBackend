package com.cuukenn.common.netty.handler.protocol;

import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import protocol.Message;

/**
 * 服务端相应ping请求
 *
 * @author changgg
 */
@Service
@Slf4j
public class ErrorInvocation implements ITransportProtocolInvocation {
    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.ERROR;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        log.error("some error happened,id:[{}],puppetName:[{}],message:[{}.{}]",
                message.getId(), message.getPuppetName(), message.getError().getCode(), message.getError().getMessage());
    }
}
