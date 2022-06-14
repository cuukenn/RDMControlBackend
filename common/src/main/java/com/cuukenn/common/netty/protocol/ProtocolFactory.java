package com.cuukenn.common.netty.protocol;


import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author changgg
 */
@Slf4j
public class ProtocolFactory {
    private static final List<IProtocolInvocation<Object>> HANDLERS = new CopyOnWriteArrayList<>();
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
        for (IProtocolInvocation<Object> handler : HANDLERS) {
            if (handler.support(message)) {
                handler.invoke(ctx, message);
                return;
            }
        }
        NO_OPTION_HANDLER.invoke(ctx, message);
    }

    public static void addHandlers(IProtocolInvocation<Object> handler) {
        if (handler != null) {
            HANDLERS.add(handler);
        }
    }
}
