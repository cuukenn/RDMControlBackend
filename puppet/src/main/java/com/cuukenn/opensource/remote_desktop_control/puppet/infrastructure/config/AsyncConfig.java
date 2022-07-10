package com.cuukenn.opensource.remote_desktop_control.puppet.infrastructure.config;

import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.util.CustomerRejectedExecutionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * @author changgg
 */
@Component
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {
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
}
