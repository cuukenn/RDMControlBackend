package com.cuukenn.opensource.remote_desktop_control.core.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ErrorPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.ui.util.UIUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class MessageErrorInvocation extends ErrorInvocation {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ErrorPacket message) throws Exception {
        super.channelRead0(ctx, message);
        UIUtil.errorMessage(message.getCode(), message.getMessage());
    }
}
