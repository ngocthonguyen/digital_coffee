package com.digitalcoffee.order.controller;

import com.digitalcoffee.order.dto.Order;
import com.digitalcoffee.order.service.OrderService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "")
    public List<Order> findOrdersByCustomerId(@RequestParam("customerUsername") @NonNull String customerUsername) {
        return orderService.findOrderByCustomerUsername(customerUsername);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Order> findOrdersById(@PathVariable("orderId") @NonNull Long orderId) {
        return orderService.findOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/")
    public Order save(@RequestBody Order order){
        return orderService.create(order);
    }
}
