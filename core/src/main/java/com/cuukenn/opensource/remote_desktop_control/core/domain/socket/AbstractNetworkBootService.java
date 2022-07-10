package com.cuukenn.opensource.remote_desktop_control.core.domain.socket;

import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.ClientChannelInitializer;
import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.client.config.BaseNetworkClientProperties;
import com.cuukenn.opensource.remote_desktop_control.core.domain.ui.util.UIUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractNetworkBootService implements INetworkBootService {
    protected final BaseNetworkClientProperties properties;
    private final NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private volatile boolean canReconnect = false;
    private volatile Channel channel;

    @Override
    public Optional<Channel> getChannel() {
        return Optional.ofNullable(channel);
    }

    @Override
    public Optional<Channel> getChannelOrError() {
        Optional<Channel> channel = getChannel();
        if (!channel.isPresent()) {
            UIUtil.errorMessage(-300, "与服务器连接异常，请连接服务器或稍后再试");
        }
        return channel;
    }

    /**
     * 启动netty
     */
    public synchronized void start() {
        if (channel != null && channel.isActive()) {
            return;
        }
        canReconnect = true;
        initBootstrap()
                .handler(getChannelHandler0())
                .connect().addListener((ChannelFutureListener) futureListener -> {
                    if (!futureListener.isSuccess()) {
                        if (!canReconnect) {
                            return;
                        }
                        log.info("failed to connect to server, try connect after 10s");
                        futureListener.channel().eventLoop().schedule(this::reconnect, 10, TimeUnit.SECONDS);
                        return;
                    }
                    channel = futureListener.channel();
                    log.info("connect to server[{}:{}] successfully!", properties.getServerAddress(), properties.getPort());
                });
    }

    abstract protected Bootstrap initBootstrap();

    protected ChannelHandler getChannelHandler0() {
        return new ClientChannelInitializer(properties, this);
    }

    /**
     * 重新连接
     */
    @Override
    public synchronized void reconnect() {
        if (!canReconnect) {
            return;
        }
        eventLoopGroup.schedule(() -> {
            try {
                this.start();
            } catch (Exception e) {
                log.error("reconnect failed", e);
            }
        }, 10, TimeUnit.SECONDS);
        log.info("failed to connect to server, try connect after 10s");
    }

    /**
     * 停止netty服务
     */
    @Override
    public synchronized void stop() {
        canReconnect = false;
        if (channel != null) {
            channel.close();
            channel.disconnect();
            channel = null;
        }
    }
}
