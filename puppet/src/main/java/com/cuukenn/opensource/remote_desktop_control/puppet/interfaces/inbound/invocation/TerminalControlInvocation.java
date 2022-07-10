package com.cuukenn.opensource.remote_desktop_control.puppet.interfaces.inbound.invocation;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.DisconnectPacket;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen.ScreenSenderService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class TerminalControlInvocation extends SimpleChannelInboundHandler<DisconnectPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DisconnectPacket msg) throws Exception {
        log.info("disconnect puppet successful");
        SpringUtil.getBean(ScreenSenderService.class).stopSnapshotSender();
    }
}
