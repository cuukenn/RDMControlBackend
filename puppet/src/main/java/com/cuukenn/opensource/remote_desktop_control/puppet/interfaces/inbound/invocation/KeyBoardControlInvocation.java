package com.cuukenn.opensource.remote_desktop_control.puppet.interfaces.inbound.invocation;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.input.KeyBoardControlPacket;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen.IReplay;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author changgg
 */
@Slf4j
public class KeyBoardControlInvocation extends SimpleChannelInboundHandler<KeyBoardControlPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, KeyBoardControlPacket msg) throws Exception {
        SpringUtil.getBean(IReplay.class).keyBoardAction(msg);
    }
}
