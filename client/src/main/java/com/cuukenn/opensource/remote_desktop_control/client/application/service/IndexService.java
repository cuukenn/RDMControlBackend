package com.cuukenn.opensource.remote_desktop_control.client.application.service;

import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.ConnectEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.ConnectPuppetEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.DisconnectEvent;
import com.cuukenn.opensource.remote_desktop_control.client.domain.ui.event.DisconnectPuppetEvent;
import com.cuukenn.opensource.remote_desktop_control.core.domain.event.AbstractRegistrableEventHandler;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.PackUtil;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ConnectPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.DisconnectPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.TCPNetworkClient;
import com.google.common.eventbus.Subscribe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author changgg
 */
@SuppressWarnings("unused")
@Service
@RequiredArgsConstructor
@Slf4j
public class IndexService extends AbstractRegistrableEventHandler {
    private final TCPNetworkClient nettyTCPClient;

    @Subscribe
    public void connect(ConnectEvent event) {
        log.info("connect to server");
        nettyTCPClient.start();
    }

    @Subscribe
    public void disconnect(DisconnectEvent event) {
        log.info("disconnect to server");
        nettyTCPClient.stop();
    }

    @Subscribe
    public void connectPuppet(ConnectPuppetEvent event) {
        log.info("connect to puppet");
        nettyTCPClient.getChannelOrError()
                .ifPresent(channel -> {
                    log.info("send connect puppet message");
                    ConnectPacket packet = new ConnectPacket();
                    PackUtil.fillPuppetId(packet, event.getPuppetId());
                    channel.writeAndFlush(packet);
                });
    }

    @Subscribe
    public void disconnectPuppet(DisconnectPuppetEvent event) {
        nettyTCPClient.getChannelOrError()
                .ifPresent(channel -> {
                    log.info("send disconnect puppet message");
                    DisconnectPacket packet = new DisconnectPacket();
                    PackUtil.fillPuppetId(packet, event.getPuppetId());
                    channel.writeAndFlush(new DisconnectPacket());
                });
    }
}
