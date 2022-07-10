package com.cuukenn.opensource.remote_desktop_control.server.infrastructure.util;

import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.exception.BizException;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChannelHolderUtil {
    private static final String CURRENT_KEY = "current_key";
    private static final String BIND_KEY = "bind_key";
    private static final ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    /**
     * 上线一个客户端
     *
     * @param id      clientId
     * @param channel channel
     */
    public static void online(String id, Channel channel) {
        channelMap.putIfAbsent(id, channel);
        AttributeKey<String> key = AttributeKey.valueOf(CURRENT_KEY);
        channel.attr(key).set(id);
    }

    /**
     * 下线一个客户端
     *
     * @param channel channel
     */
    public static void offline(Channel channel) {
        unbind(channel);
        AttributeKey<String> current = AttributeKey.valueOf(CURRENT_KEY);
        String currentKey = channel.attr(current).get();
        if (currentKey != null) {
            channelMap.remove(currentKey);
        }
    }

    /**
     * 绑定客户端和傀儡机
     *
     * @param clientId 客户端ID
     * @param puppetId 傀儡机ID
     * @return 是否绑定成功
     */
    public static void bind(String clientId, String puppetId) {
        if (clientId == null || puppetId == null) {
            throw new BizException(-200, "客户端ID或者傀儡机ID为空,无法连接");
        }
        Channel client = channelMap.get(clientId);
        Channel puppet = channelMap.get((puppetId));
        if (client == null || puppet == null) {
            throw new BizException(-200, "傀儡及未上线");
        }
        AttributeKey<String> key = AttributeKey.valueOf(BIND_KEY);
        String puppetHasBind = client.attr(key).get();
        if (puppetHasBind != null && !puppetHasBind.equalsIgnoreCase(puppetId)) {
            throw new BizException(-200, "傀儡机已处于 被控状态");
        }
        String clientHasBind = puppet.attr(key).get();
        if (clientHasBind != null && !clientHasBind.equalsIgnoreCase(clientId)) {
            unbind(client);
        }
        boolean rs1 = client.attr(key).compareAndSet(puppetHasBind, puppetId);
        boolean rs2 = puppet.attr(key).compareAndSet(clientHasBind, clientId);
        if (!rs1 && !rs2) {
            throw new BizException(-200, "连接失败，请稍后再试");
        }
    }

    /**
     * 解绑
     *
     * @param channel channel
     */
    public static void unbind(Channel channel) {
        AttributeKey<String> key = AttributeKey.valueOf(BIND_KEY);
        if (channel != null) {
            String pair = channel.attr(key).getAndSet(null);
            if (pair == null) {
                return;
            }
            unbind(channelMap.get(pair));
        }
    }

    /**
     * 获取绑定的channel
     *
     * @param channel 当前channel
     * @return channel
     */
    public static Optional<Channel> getAnotherPair(Channel channel) {
        if (channel == null) {
            return Optional.empty();
        }
        AttributeKey<String> key = AttributeKey.valueOf(BIND_KEY);
        String pair = channel.attr(key).get();
        if (pair == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(channelMap.get(pair));
    }
}
