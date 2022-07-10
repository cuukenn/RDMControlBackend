package com.cuukenn.opensource.remote_desktop_control.server.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.DisconnectPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ScreenPacket;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * 处理消息
 *
 * @author changgg
 */
@Service
public class ScreenInvocation extends AbstractForwardMessageInvocation<ScreenPacket> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.writeAndFlush(new DisconnectPacket());
        super.exceptionCaught(ctx, cause);
    }
}