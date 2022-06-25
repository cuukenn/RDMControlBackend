package com.cuukenn.puppet.ui;

import com.cuukenn.common.netty.client.handler.NettyClient;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author changgg
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class UISpringRunner implements CommandLineRunner {
    private final NettyClient client;

    @Override
    public void run(String... args) throws Exception {
        client.start();
        Application.launch(UIApplication.class, args);
    }
}
