package com.cuukenn.puppet;

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

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(PuppetApplication.class);
    }
}
