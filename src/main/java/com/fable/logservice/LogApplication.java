package com.fable.logservice;

import com.fable.logservice.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

// @ServletComponentScan
@SpringBootApplication
@Import({SecurityConfig.class})
@EnableJpaRepositories("com.fable.logservice.repo")
@EntityScan("com.fable.logservice.repo.model")
public class LogApplication {
    public static void main(String[] args) {
        SpringApplication.run(LogApplication.class, args);
    }
}