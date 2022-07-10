
package com.cuukenn.opensource.remote_desktop_control.client.interfaces.inbound.invocation;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.controller.IndexController;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ConnectPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.ui.util.UIUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端相应ping请求
 *
 * @author changgg
 */
@Slf4j
public class ConnectedInvocation extends SimpleChannelInboundHandler<ConnectPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ConnectPacket msg) throws Exception {
        log.info("connect puppet successful");
        UIUtil.runUITask(() -> invoke0(msg));
    }

    private synchronized void invoke0(ConnectPacket msg) {
        SpringUtil.getBean(IndexController.class).openScreenControl(msg.getPuppetId());
    }
}
