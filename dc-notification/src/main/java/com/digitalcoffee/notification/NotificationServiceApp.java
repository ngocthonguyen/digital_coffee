package com.digitalcoffee.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NotificationServiceApp {

    public static void main(String[] args)  {
        SpringApplication.run(NotificationServiceApp.class, args);            // it wil start application
    }

    @Bean
    DispatcherServlet dispatcherServlet() {     // To allow RequestContextHolder in ClientHttpRequestInterceptor
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setThreadContextInheritable(true);
        return servlet;
    }

    @Bean
    @Primary
    public RestTemplate orderService(){
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
                httpRequest.getHeaders().add("Authorization", getBearerTokenHeader());
                return clientHttpRequestExecution.execute(httpRequest,bytes);
            }
        });
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean(name = "tokenVerifier")
    public RestTemplate tokenVerifier(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    public String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

}
