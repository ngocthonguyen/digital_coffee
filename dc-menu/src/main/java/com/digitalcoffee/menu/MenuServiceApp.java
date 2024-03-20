package com.digitalcoffee.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class MenuServiceApp {
    public static void main(String[] args)  {
        SpringApplication.run(MenuServiceApp.class, args);            // it wil start application
    }

    @Bean
    DispatcherServlet dispatcherServlet() {     // To allow RequestContextHolder in ClientHttpRequestInterceptor
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setThreadContextInheritable(true);
        return servlet;
    }

}
