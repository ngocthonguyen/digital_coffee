package com.digitalcoffee.order.service;

import com.digitalcoffee.order.dto.Order;
import com.digitalcoffee.order.mapper.OrderMapper;
import com.digitalcoffee.order.model.Notification;
import com.digitalcoffee.order.model.OrderRoot;
import com.digitalcoffee.order.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderDetailsRepository orderRepository;

    @Autowired
    private NotificationServiceClient notificationServiceClient;

    @Autowired
    private OrderMapper mapper;

    public Order create(Order order){
        OrderRoot orderRoot = orderRepository.save(mapper.mapToEntity(order));

        Notification notification = new Notification();
        notification.setUsername(order.getCustomerUsername());
        notification.setNotificationType("SMS");
        notification.setMobileNo("99 99 99 99 99");
        notificationServiceClient.sendNotification(notification);

        return mapper.mapToDto(orderRoot);
    }

    public List<Order> findOrderByCustomerUsername(String customerId){
        return this.orderRepository.findOrdersByCustomerUsername(customerId)
                .stream().map(mapper::mapToDto).toList();
    }

    public Optional<Order> findOrderById(Long orderId) {
        return this.orderRepository.findById(orderId).map(mapper::mapToDto);
    }

}
