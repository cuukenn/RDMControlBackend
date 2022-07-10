package com.cuukenn.opensource.remote_desktop_control.puppet.domain.screen;

import com.cuukenn.opensource.remote_desktop_control.core.domain.protocol.packet.ScreenPacket;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.TCPNetworkClient;
import com.cuukenn.opensource.remote_desktop_control.puppet.domain.socket.config.NetworkProperties;
import io.netty.util.concurrent.ScheduledFuture;
import javafx.stage.Screen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@Service
@Slf4j
public class ScreenSenderService {
    private final TCPNetworkClient client;
    private final Runnable task;
    private volatile ScheduledFuture<?> future;

    public ScreenSenderService(TCPNetworkClient client, IReplay replay, NetworkProperties properties) {
        this.client = client;
        this.task = () -> client.getChannel().ifPresent(
                channel -> {
                    Screen primary = Screen.getPrimary();
                    channel.writeAndFlush(new ScreenPacket(properties.getPuppetId(), primary.getOutputScaleX(), primary.getOutputScaleY(), replay.getScreenSnapshot()));
                }
        );
    }

    public synchronized void startSnapshotSender() {
        if (future != null) {
            return;
        }
        try {
            client.getChannel().ifPresent(channel -> future = channel.eventLoop().scheduleWithFixedDelay(task, 0, 50, TimeUnit.MICROSECONDS));
        } catch (RuntimeException e) {
            log.error("startSnapshotSender error", e);
        }
    }

    public synchronized void stopSnapshotSender() {
        if (future == null) {
            return;
        }
        try {
            future.cancel(true);
            future = null;
        } catch (RuntimeException e) {
            log.error("stopSnapshotSender error", e);
        }
    }
}
