package com.cuukenn.client.netty.handler.protocol;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.client.ui.controller.IndexController;
import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import com.cuukenn.common.netty.util.UIUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import protocol.Message;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 处理消息
 *
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ScreenUpdateInvocation implements ITransportProtocolInvocation {
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            ThreadUtil.newNamedThreadFactory("screen-update-", false),
            new ThreadPoolExecutor.DiscardPolicy());

    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.SCREEN;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        executor.execute(() -> UIUtil.runUITask(() -> SpringUtil.getBean(IndexController.class).refreshScreenImage(message.getScreen().getData().toByteArray())));
    }
}