package com.cuukenn.opensource.remote_desktop_control.client.infrastructure.config;

import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.util.CustomerRejectedExecutionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author changgg
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {
    /**
     * 屏幕和控制队列分开、屏幕刷新专用队列
     */
    public static final String SCREEN_EXECUTOR = "screen-update-executor";

    @Override
    public Executor getAsyncExecutor() {
        log.info("config default async task executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(1024);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("async-default-executor-");
        executor.setDaemon(false);
        executor.setRejectedExecutionHandler(new CustomerRejectedExecutionHandler());
        executor.initialize();
        return executor;
    }

    @Bean(SCREEN_EXECUTOR)
    public Executor screenSnapshotExecutor() {
        log.info("config screen async task executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(256);
        executor.setKeepAliveSeconds(0);
        executor.setThreadNamePrefix("async-screen-executor-");
        executor.setDaemon(false);
        executor.setRejectedExecutionHandler((r, e) -> {
            log.warn("too many screenshot,skip oldest task!");
            if (!e.isShutdown()) {
                e.getQueue().poll();
                e.execute(r);
            }
        });
        executor.initialize();
        return executor;
    }
}
