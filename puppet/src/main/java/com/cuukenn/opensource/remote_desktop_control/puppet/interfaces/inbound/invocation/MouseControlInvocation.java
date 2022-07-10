package com.cuukenn.opensource.remote_desktop_control.puppet.interfaces.inbound.invocation;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.MouseControlPacket;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen.IReplay;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class MouseControlInvocation extends SimpleChannelInboundHandler<MouseControlPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MouseControlPacket msg) throws Exception {
        SpringUtil.getBean(IReplay.class).mouseAction(msg);
    }
}
