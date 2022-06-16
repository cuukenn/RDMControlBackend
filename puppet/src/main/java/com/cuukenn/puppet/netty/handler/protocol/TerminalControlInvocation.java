package com.cuukenn.puppet.netty.handler.protocol;

import com.cuukenn.common.netty.protocol.ITransportProtocolInvocation;
import com.cuukenn.puppet.service.ScreenSenderService;
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
public class TerminalControlInvocation implements ITransportProtocolInvocation {
    private final ScreenSenderService screenSenderService;

    @Override
    public Message.ProtocolType getSupportType() {
        return Message.ProtocolType.DISCONNECT;
    }

    @Override
    public void invoke(ChannelHandlerContext ctx, Message.TransportProtocol message) {
        log.info("connect puppet successful");
        screenSenderService.stopSnapshotSender();
    }
}
