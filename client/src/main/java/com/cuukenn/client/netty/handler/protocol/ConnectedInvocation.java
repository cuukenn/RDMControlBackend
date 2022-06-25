
package com.cuukenn.client.netty.handler.protocol;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.client.ui.controller.IndexController;
import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import com.cuukenn.common.netty.util.UIUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import protocol.Message;

/**
 * 服务端相应ping请求
 *
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectedInvocation implements ITransportProtocolInvocation {
    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.CONNECT;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        log.info("connect puppet successful");
        UIUtil.runUITask(this::invoke0);
    }

    private synchronized void invoke0() {
        SpringUtil.getBean(IndexController.class).openScreenTab();
    }
}
