package com.cuukenn.opensource.remote_desktop_control.core.infrastructure.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author changgg
 */
@Slf4j
public class CustomerRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.warn("too many task,use current thread to run it!");
        if (!executor.isShutdown()) {
            r.run();
        }
    }
}
