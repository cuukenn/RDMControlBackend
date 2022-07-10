package com.cuukenn.opensource.remote_desktop_control.client.interfaces.inbound.invocation;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.controller.IndexController;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ScreenPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.ui.util.UIUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 处理消息
 *
 * @author changgg
 */
@Slf4j
@RequiredArgsConstructor
public class ScreenUpdateInvocation extends SimpleChannelInboundHandler<ScreenPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ScreenPacket msg) throws Exception {
        UIUtil.runUITask(() -> SpringUtil.getBean(IndexController.class).refreshScreen(msg));
    }
}