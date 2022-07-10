package com.cuukenn.opensource.remote_desktop_control.puppet.interfaces.inbound.invocation;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ConnectPacket;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen.ScreenSenderService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class StartControlInvocation extends SimpleChannelInboundHandler<ConnectPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ConnectPacket msg) throws Exception {
        log.info("connect puppet successful");
        SpringUtil.getBean(ScreenSenderService.class).startSnapshotSender();
    }
}
