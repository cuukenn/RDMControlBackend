
package com.cuukenn.opensource.remote_desktop_control.client.interfaces.inbound.invocation;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.DisconnectPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端相应ping请求
 *
 * @author changgg
 */
@Slf4j
public class TerminalControlInvocation extends SimpleChannelInboundHandler<DisconnectPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DisconnectPacket msg) throws Exception {
        log.info("disconnect puppet successful");
    }
}
