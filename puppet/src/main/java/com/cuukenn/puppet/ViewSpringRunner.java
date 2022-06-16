package com.cuukenn.puppet;

import com.cuukenn.common.netty.client.handler.NettyClient;
import com.cuukenn.puppet.ui.ViewLaunch;
import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author changgg
 */
@Component
@RequiredArgsConstructor
public class ViewSpringRunner implements CommandLineRunner {
    private final NettyClient client;

    @Override
    public void run(String... args) throws Exception {
        client.start();
        Application.launch(ViewLaunch.class, args);
    }
}
