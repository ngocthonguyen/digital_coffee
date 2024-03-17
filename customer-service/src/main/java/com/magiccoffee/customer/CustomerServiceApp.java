package com.magiccoffee.customer;

import com.degitalcoffee.commons.CustomClientHttpRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan
public class CustomerServiceApp {

    public static void main(String[] args)  {
        SpringApplication.run(CustomerServiceApp.class, args);            // it wil start application
    }

    @Bean
    DispatcherServlet dispatcherServlet() {     // To allow RequestContextHolder in ClientHttpRequestInterceptor
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setThreadContextInheritable(true);
        return servlet;
    }

    @Bean("dcRestTemplate")
    public RestTemplate orderService(){
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new CustomClientHttpRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

}
