package com.examples.dddtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DddtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DddtestApplication.class, args);
    }

}
