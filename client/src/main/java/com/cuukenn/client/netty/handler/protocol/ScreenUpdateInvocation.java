package com.cuukenn.client.netty.handler.protocol;

import com.cuukenn.client.ui.FxmlConstant;
import com.cuukenn.client.ui.controller.ControlScreen;
import com.cuukenn.common.netty.client.ui.StageController;
import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import io.netty.channel.ChannelHandlerContext;
import javafx.application.Platform;
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
    private final StageController stageController;

    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.SCREEN;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        log.info("screen data,{}", message.getScreen().getData().toStringUtf8());
        Platform.runLater(() -> ((ControlScreen) stageController.getStage(FxmlConstant.CONTROL_SCREEN).getController()).drawImage(message.getScreen().getData().toByteArray()));
    }
}