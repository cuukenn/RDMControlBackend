package com.cuukenn.opensource.remote_desktop_control.server.domain.socket;

import com.cuukenn.opensource.remote_desktop_control.core.domain.socket.INetworkBootService;
import com.cuukenn.opensource.remote_desktop_control.server.domain.socket.config.ServerNetworkProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Optional;

/**
 * @author changgg
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class NetworkServer implements INetworkBootService {
    private final NioEventLoopGroup boss = new NioEventLoopGroup();
    private final NioEventLoopGroup work = new NioEventLoopGroup();
    private final ServerNetworkProperties properties;
    private volatile Channel channel;

    @Override
    public Optional<Channel> getChannel() {
        return Optional.ofNullable(channel);
    }

    @Override
    public Optional<Channel> getChannelOrError() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void reconnect() {
        throw new UnsupportedOperationException();
    }

    /**
     * 启动netty
     */
    @PostConstruct
    @SneakyThrows
    public void start() {
        if (channel != null && channel.isActive()) {
            return;
        }
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(properties.getPort())
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ServerChannelInitializer(properties));
        bootstrap.bind().sync().addListener((ChannelFutureListener) futureListener -> {
            if (futureListener.isSuccess()) {
                log.info("start server on port:[{}]", properties.getPort());
            }
            channel = futureListener.channel();
        });
    }

    /**
     * 停止netty服务
     */
    @PreDestroy
    @Override
    public void stop() {
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 10 * 60 * 1000)
    public void removeInactiveChannelPair() {
        log.info("start check channel pair");
        log.info("start check channel pair,remove {} channelPair", 0);
    }
}
