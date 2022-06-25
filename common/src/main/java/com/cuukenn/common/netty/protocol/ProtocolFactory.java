package com.cuukenn.common.netty.protocol;


import cn.hutool.extra.spring.SpringUtil;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changgg
 */
@Slf4j
public class ProtocolFactory {
    private static volatile List<IProtocolInvocation<Object>> HANDLERS;
    private static final IProtocolInvocation<Object> NO_OPTION_HANDLER = new IProtocolInvocation<Object>() {
        @Override
        public boolean support(Object message) {
            return false;
        }

        @Override
        public void invoke(ChannelHandlerContext ctx, Object message) {
            log.warn("no option found,message:[{}]", message);
        }
    };

    public static void handle(ChannelHandlerContext ctx, Object message) {
        if (HANDLERS == null) {
            synchronized (ProtocolFactory.class) {
                if (HANDLERS == null) {
                    List<IProtocolInvocation<Object>> tmp = new ArrayList<>();
                    SpringUtil.getBeansOfType(IProtocolInvocation.class).values().forEach(invocation -> {
                        //noinspection unchecked
                        tmp.add(invocation);
                        log.info("load message invocation {}", invocation);
                    });
                    HANDLERS = tmp;
                }
            }
        }
        for (IProtocolInvocation<Object> handler : HANDLERS) {
            if (handler.support(message)) {
                handler.invoke(ctx, message);
                return;
            }
        }
        NO_OPTION_HANDLER.invoke(ctx, message);
    }
}
