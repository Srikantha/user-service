package com.ing.assignment.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class UserServiceApplication {

    @PostConstruct
    public void startupApplication() {
        log.info("-------------------------------------------------");
        log.info("--------User Service Application Started---------");
        log.info("-------------------------------------------------");
    }

    @PreDestroy
    public void shutdownApplication() {
        log.info("-------------------------------------------------");
        log.info("--------User Service Application Stopped---------");
        log.info("-------------------------------------------------");
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
