package com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.PackUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ConnectPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ErrorPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.util.IdUtil;
import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.exception.BizException;
import com.cuukenn.opensource.remote_desktop_control.server.infrastructure.util.ChannelHolderUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changgg
 */
public class ConnectInvocation extends SimpleChannelInboundHandler<ConnectPacket> {
    private static final Map<String, ChannelId> map = new ConcurrentHashMap<>();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ConnectPacket msg) throws Exception {
        try {
            ChannelHolderUtil.bind(IdUtil.getMachineId(msg.getId()), msg.getPuppetId());
            ConnectPacket connectPacket = new ConnectPacket();
            PackUtil.fillPuppetId(connectPacket, msg.getPuppetId());
            ctx.writeAndFlush(connectPacket);
            ChannelHolderUtil.getAnotherPair(ctx.channel()).ifPresent(channel -> channel.writeAndFlush(connectPacket));
        } catch (BizException e) {
            ctx.writeAndFlush(new ErrorPacket(e.getCode(), e.getMessage()));
        }
    }
}
