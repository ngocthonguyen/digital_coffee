package com.magiccoffee.customer.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
public class OrderServiceProxy {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    @Qualifier("dcRestTemplate")
    private RestTemplate restTemplate;

    public List<Order> getCustomerOrders(String username){
        ServiceInstance serviceInstance = discoveryClient.getInstances("API-GATEWAY-SERVICE").iterator().next();
        String url = serviceInstance.getUri().toString() + "/orders?customerUsername=" + username;
        return List.of(Objects.requireNonNull(restTemplate.getForEntity(url, Order[].class).getBody()));
    }
}
