package com.cuukenn.opensource.remote_desktop_control.client.application.service;

import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.MouseControlEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.keyBoardBoardControlEvent;
import com.cuukenn.opensource.remote_desktop_control.core.domain.event.AbstractRegistrableEventHandler;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.TCPNetworkClient;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ScreenService extends AbstractRegistrableEventHandler {
    private final TCPNetworkClient tcpNettyClient;

    @Subscribe
    public void keyBoardEvent(keyBoardBoardControlEvent event) {
        log.debug("keyBoardEvent,event:{}", event);
        tcpNettyClient.getChannel().ifPresent(channel -> channel.writeAndFlush(event.getPacket()));
    }

    @Subscribe
    public void mouseEvent(MouseControlEvent event) {
        log.debug("keyBoardEvent,event:{}", event);
        tcpNettyClient.getChannel().ifPresent(channel -> channel.writeAndFlush(event.getPacket()));
    }
}
