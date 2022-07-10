package com.cuukenn.opensource.remote_desktop_control.client;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author changgg
 */
@SpringBootApplication
@EnableSpringUtil
public class ClientApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ClientApplication.class);
    }
}
