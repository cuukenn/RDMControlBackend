package com.cuukenn.puppet;

import com.cuukenn.puppet.ui.ViewLaunch;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author changgg
 */
@Component
public class ViewSpringRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Application.launch(ViewLaunch.class, args);
    }
}
