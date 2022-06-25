package com.cuukenn.client.ui;

import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author changgg
 */
@Component
public class UISpringRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Application.launch(UIApplication.class, args);
    }
}
