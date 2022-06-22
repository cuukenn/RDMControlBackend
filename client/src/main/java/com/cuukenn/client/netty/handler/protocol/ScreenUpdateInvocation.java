package com.cuukenn.client.netty.handler.protocol;

import cn.hutool.extra.spring.SpringUtil;
import com.cuukenn.client.ui.controller.IndexController;
import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import com.cuukenn.common.netty.util.UIUtil;
import io.netty.channel.ChannelHandlerContext;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import protocol.Message;

/**
 * 处理消息
 *
 * @author changgg
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ScreenUpdateInvocation implements ITransportProtocolInvocation {

    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.SCREEN;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        log.debug("screen data,{}", message.getScreen().getData().toStringUtf8());
        UIUtil.runUITask(() -> {
            ObservableList<Tab> tabs = SpringUtil.getBean(IndexController.class).getTabPane().getTabs();
            if (tabs.size() > 0) {
                ((TextField) tabs.get(1).getContent()).setText(message.getScreen().getData().toStringUtf8());
            }
        });
    }
}