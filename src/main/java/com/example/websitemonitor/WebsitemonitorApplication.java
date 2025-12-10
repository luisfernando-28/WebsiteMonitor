package com.example.websitemonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // 👈 ISSO ATIVA O SCHEDULER
public class WebsitemonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsitemonitorApplication.class, args);
    }
}