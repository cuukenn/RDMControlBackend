package com.cuukenn.puppet.service;

import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.common.netty.enums.ApplicationType;
import com.cuukenn.common.netty.util.ProtocolUtil;
import com.cuukenn.puppet.control.IReplay;
import com.google.protobuf.ByteString;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import protocol.Message;

import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@Service
@Slf4j
public class ScreenSenderService {
    private final NettyClient client;
    private final Runnable task;
    private volatile ScheduledFuture<?> future;

    public ScreenSenderService(NettyClient client, IReplay replay) {
        this.client = client;
        this.task = () -> client.getChannel().ifPresent(
                channel -> {
                    byte[] screenSnapshot = replay.getScreenSnapshot();
                    channel.writeAndFlush(
                            ProtocolUtil.createProtocol(ApplicationType.PUPPET, Message.ProtocolType.SCREEN)
                                    .setScreen(Message.ScreenMessage.newBuilder().setData(ByteString.copyFrom(screenSnapshot)))
                                    .build()
                    );
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
