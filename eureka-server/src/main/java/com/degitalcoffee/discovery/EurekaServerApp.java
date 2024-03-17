package com.degitalcoffee.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Indicate Eureka Server Application
public class EurekaServerApp {
    public static void main(String[] args)  {
        SpringApplication.run(EurekaServerApp.class, args);            // it wil start application
    }
}
