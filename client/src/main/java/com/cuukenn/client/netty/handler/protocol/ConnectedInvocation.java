
package com.cuukenn.client.netty.handler.protocol;

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
public class ConnectedInvocation implements ITransportProtocolInvocation {
    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.CONNECT_RESPONSE;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        log.info("connect puppet successful");
    }
}
