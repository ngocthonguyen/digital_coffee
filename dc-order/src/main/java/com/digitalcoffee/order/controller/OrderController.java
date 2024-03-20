package com.digitalcoffee.order.controller;

import com.digitalcoffee.order.dto.Order;
import com.digitalcoffee.order.dto.OrderCreationRequest;
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

    @GetMapping(value = "/customer")
    public List<Order> findOrdersByCustomerId(@RequestParam("username") @NonNull String username) {
        return orderService.findOrdersByCustomerUsername(username);
    }

    @GetMapping(value = "/shop")
    public List<Order> findOrdersByShopRef(@RequestParam("shopRef") @NonNull String shopRef) {
        return orderService.findOrdersByShopRef(shopRef);
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Order> findOrdersById(@PathVariable("orderId") @NonNull Long orderId) {
        return orderService.findOrderById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/")
    public Order create(@RequestBody OrderCreationRequest order){
        return orderService.create(order);
    }

    @PutMapping(value = "/{orderId}/newStatus")
    public Order update(
            @PathVariable("orderId") Long orderId,
            @RequestParam("newStatus") String newStatus){
        return orderService.updateOrderStatus(orderId, newStatus);
    }
}
