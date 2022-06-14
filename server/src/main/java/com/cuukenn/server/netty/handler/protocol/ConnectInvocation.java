package com.cuukenn.server.netty.handler.protocol;

import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import com.cuukenn.common.netty.util.ProtocolUtil;
import com.cuukenn.server.netty.handler.bound.ConnectionHolderTrigger;
import com.cuukenn.server.netty.pojo.ChannelPair;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;
import protocol.Message;

/**
 * 处理消息
 *
 * @author changgg
 */
@Service
public class ConnectInvocation implements ITransportProtocolInvocation {
    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.CONNECT_REQUEST;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        ChannelPair channelPair = ConnectionHolderTrigger.getChannelPair(message.getPuppetName());
        if (channelPair == null) {
            ctx.writeAndFlush(ProtocolUtil.createError(ApplicationType.SERVER, 100, "傀儡机未上线或傀儡机名不正确"));
            return;
        }
        Channel puppetChannel;
        if ((puppetChannel = channelPair.getPuppet()) == null) {
            ctx.writeAndFlush(ProtocolUtil.createError(ApplicationType.SERVER, 100, "傀儡机未上线,请稍后再试"));
            return;
        }
        if (!puppetChannel.isActive()) {
            ctx.writeAndFlush(ProtocolUtil.createError(ApplicationType.SERVER, 100, "傀儡机连接异常,请稍后再试"));
            return;
        }
        Channel clientChannel;
        boolean flag = false;
        if ((clientChannel = channelPair.getClient()) != null && !(flag = clientChannel.equals(ctx.channel()))) {
            ctx.writeAndFlush(ProtocolUtil.createError(ApplicationType.SERVER, 100, "傀儡机已经被其他客户端控制,请稍后再试"));
            return;
        }
        if (!flag) {
            channelPair.setClient(ctx.channel());
        }
        ctx.writeAndFlush(ProtocolUtil.createProtocol(ApplicationType.SERVER).setType(Message.ProtocolType.CONNECT_RESPONSE));
    }
}
