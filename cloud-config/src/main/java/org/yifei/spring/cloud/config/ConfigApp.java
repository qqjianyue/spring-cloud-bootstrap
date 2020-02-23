package org.yifei.spring.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApp {

    public static void main(String[] args) {
        System.out.println("Current Folder ${user.dir}: " + System.getProperty("user.dir"));
        SpringApplication.run(ConfigApp.class, args);
    }
}
