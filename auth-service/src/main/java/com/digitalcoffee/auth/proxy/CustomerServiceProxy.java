package com.digitalcoffee.auth.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerServiceProxy {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("dcRestTemplate")
    private RestTemplate restTemplate;

    public void createCustomer(String username){
        ServiceInstance serviceInstance = discoveryClient.getInstances("API-GATEWAY-SERVICE").iterator().next();
        restTemplate.postForLocation(serviceInstance.getUri().toString() + "/customers?username=" + username,null);
    }
}
