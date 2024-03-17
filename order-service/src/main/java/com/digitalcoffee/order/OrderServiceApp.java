package com.digitalcoffee.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class OrderServiceApp {
    public static void main(String[] args)  {
        SpringApplication.run(OrderServiceApp.class, args);            // it wil start application
    }

    @Bean
    DispatcherServlet dispatcherServlet() {     // To allow RequestContextHolder in ClientHttpRequestInterceptor
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setThreadContextInheritable(true);
        return servlet;
    }

    @Bean(name = "tokenVerifier")
    public RestTemplate tokenVerifier(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

}
