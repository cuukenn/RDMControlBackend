
package com.cuukenn.client.netty.handler.protocol;

import com.cuukenn.client.ui.FxmlConstant;
import com.cuukenn.common.netty.client.ui.StageController;
import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
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
    private final StageController stageController;

    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.CONNECT;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        log.info("connect puppet successful");
        stageController.setStage(FxmlConstant.CONTROL_SCREEN, FxmlConstant.MAIN);
    }
}
