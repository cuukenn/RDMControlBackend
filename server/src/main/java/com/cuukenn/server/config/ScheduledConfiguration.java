package com.cuukenn.server.config;

import cn.hutool.core.thread.ThreadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author changgg
 */
@Configuration
@EnableScheduling
public class ScheduledConfiguration implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public ExecutorService taskExecutor() {
        return new ScheduledThreadPoolExecutor(10, ThreadUtil.newNamedThreadFactory("scheduled-", false), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
