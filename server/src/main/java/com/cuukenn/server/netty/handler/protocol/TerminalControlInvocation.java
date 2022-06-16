package com.cuukenn.server.netty.handler.protocol;

import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import com.cuukenn.common.netty.util.ProtocolUtil;
import com.cuukenn.server.netty.handler.bound.ConnectionHolderTrigger;
import com.cuukenn.server.netty.pojo.ChannelPair;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;
import protocol.Message;

/**
 * 处理消息
 *
 * @author changgg
 */
@Service
public class TerminalControlInvocation implements ITransportProtocolInvocation {
    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.DISCONNECT;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        ChannelPair channelPair = ConnectionHolderTrigger.getChannelPair(message.getPuppetName());
        if (channelPair == null) {
            ctx.writeAndFlush(ProtocolUtil.error(ApplicationType.SERVER, 100, "当前未进行连接"));
            return;
        }
        channelPair.setClient(null);
        ctx.writeAndFlush(ProtocolUtil.empty(ApplicationType.SERVER, Message.ProtocolType.DISCONNECT));
    }
}
