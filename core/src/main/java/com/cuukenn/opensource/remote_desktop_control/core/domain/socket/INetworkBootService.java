package com.cuukenn.opensource.remote_desktop_control.core.domain.socket;

import io.netty.channel.Channel;

import java.util.Optional;

/**
 * @author changgg
 */
public interface INetworkBootService {
    Optional<Channel> getChannel();

    Optional<Channel> getChannelOrError();

    void start();

    void reconnect();

    void stop();
}
