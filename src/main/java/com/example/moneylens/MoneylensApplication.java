package com.example.moneylens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class MoneylensApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneylensApplication.class, args);
    }

}
