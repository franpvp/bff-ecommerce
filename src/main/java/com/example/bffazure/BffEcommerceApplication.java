package com.example.bffazure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffEcommerceApplication.class, args);
    }
}
