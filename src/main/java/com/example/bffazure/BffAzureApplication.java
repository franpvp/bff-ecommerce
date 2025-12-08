package com.example.bffazure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffAzureApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffAzureApplication.class, args);
    }
}
