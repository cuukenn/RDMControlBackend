package com.cuukenn.opensource.remote_desktop_control.core.domain.event;

import cn.hutool.core.thread.NamedThreadFactory;
import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.util.CustomerRejectedExecutionHandler;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事件工厂
 *
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventFactory {
    /**
     * 注册事件
     *
     * @param handler 处理器
     */
    public static void register(IEventHandler handler) {
        syncBus().register(handler);
        asyncBus().register(handler);
    }

    /**
     * 注销事件
     *
     * @param handler 处理器
     */
    public static void unregister(IEventHandler handler) {
        syncBus().unregister(handler);
        asyncBus().unregister(handler);
    }

    /**
     * 同步发布事件
     *
     * @param event 事件
     */
    public static void postSync(IEvent event) {
        syncBus().post(event);
    }

    /**
     * 异步发布事件
     *
     * @param event 事件
     */
    public static void postAsync(IEvent event) {
        asyncBus().post(event);
    }

    private static EventBus asyncBus() {
        return AsyncEventBusHolder.EVENT_BUS;
    }

    private static EventBus syncBus() {
        return SyncEventBusHolder.EVENT_BUS;
    }

    /**
     * 懒加载单例
     */
    @Slf4j
    private static final class AsyncEventBusHolder {
        private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(
                10, 20,
                60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                new NamedThreadFactory("event-handler-", false),
                new CustomerRejectedExecutionHandler());
        static final AsyncEventBus EVENT_BUS = new AsyncEventBus(EXECUTOR_SERVICE);
    }

    /**
     * 懒加载单例
     */
    private static final class SyncEventBusHolder {
        static final EventBus EVENT_BUS = new EventBus();
    }
}
