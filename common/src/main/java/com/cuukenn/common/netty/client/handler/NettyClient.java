package com.cuukenn.common.netty.client.handler;

import com.cuukenn.common.netty.client.config.BaseNettyClientProperties;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author changgg
 */
@RequiredArgsConstructor
@Slf4j
public class NettyClient {
    private final NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private final BaseNettyClientProperties properties;
    @Getter
    private volatile Channel channel;

    /**
     * 启动netty
     */
    @PostConstruct
    public synchronized void start() {
        if (channel != null && channel.isActive()) {
            return;
        }
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .remoteAddress(properties.getServerAddress(), properties.getPort())
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new NettyClientChannelInitializer(properties, this));
        bootstrap.connect().addListener((ChannelFutureListener) futureListener -> {
            if (!futureListener.isSuccess()) {
                log.info("failed to connect to server, try connect after 10s");
                futureListener.channel().eventLoop().schedule(this::reconnect, 10, TimeUnit.SECONDS);
                return;
            }
            channel = futureListener.channel();
            log.info("connect to server[{}:{}] successfully!", properties.getServerAddress(), properties.getPort());
        });
    }

    /**
     * 重新连接
     */
    public void reconnect() {
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
    @PreDestroy
    public void stop() {
        eventLoopGroup.shutdownGracefully();
    }
}
