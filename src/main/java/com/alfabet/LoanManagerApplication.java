package com.alfabet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
@ComponentScan("com.alfabet")
public class LoanManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanManagerApplication.class, args);
    }
}
