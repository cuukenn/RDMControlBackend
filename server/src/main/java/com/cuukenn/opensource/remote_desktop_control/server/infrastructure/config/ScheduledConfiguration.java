package com.cuukenn.opensource.remote_desktop_control.server.infrastructure.config;

import cn.hutool.core.thread.ThreadUtil;
import com.cuukenn.opensource.remote_desktop_control.core.infrastructure.util.CustomerRejectedExecutionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author changgg
 */
@Component
@EnableScheduling
@Slf4j
public class ScheduledConfiguration implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        log.info("config default schedule task executor");
        taskRegistrar.setScheduler(new ScheduledThreadPoolExecutor(10, ThreadUtil.newNamedThreadFactory("scheduled-executor-", false), new CustomerRejectedExecutionHandler()));
    }
}
