package com.cuukenn.opensource.remote_desktop_control.puppet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author changgg
 */
@SpringBootApplication
public class PuppetApplication {
    static {
        System.setProperty("java.awt.headless", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(PuppetApplication.class);
    }
}
