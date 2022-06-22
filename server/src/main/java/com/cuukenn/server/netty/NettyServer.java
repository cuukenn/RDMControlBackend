package com.cuukenn.server.netty;

import com.cuukenn.server.netty.config.ServerNettyProperties;
import com.cuukenn.server.netty.handler.bound.ConnectionHolderTrigger;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author changgg
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class NettyServer {
    private final NioEventLoopGroup boss = new NioEventLoopGroup();
    private final NioEventLoopGroup work = new NioEventLoopGroup();
    private final ServerNettyProperties properties;
    @Getter
    private volatile Channel channel;

    /**
     * 启动netty
     *
     * @throws InterruptedException 终端异常
     */
    @PostConstruct
    public void start() throws InterruptedException {
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
    public void stop() {
        boss.shutdownGracefully();
        work.shutdownGracefully();
    }

    @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 10 * 60 * 1000)
    public void removeInactiveChannelPair() {
        log.info("start check channel pair");
        int removed = ConnectionHolderTrigger.removeInactiveChannelPair();
        log.info("start check channel pair,remove {} channelPair", removed);
    }
}
