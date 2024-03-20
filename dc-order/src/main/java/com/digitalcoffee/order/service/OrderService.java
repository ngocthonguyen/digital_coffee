package com.digitalcoffee.order.service;

import com.digitalcoffee.order.dto.Order;
import com.digitalcoffee.order.dto.OrderCreationRequest;
import com.digitalcoffee.order.mapper.OrderMapper;
import com.digitalcoffee.order.model.Notification;
import com.digitalcoffee.order.model.OrderRoot;
import com.digitalcoffee.order.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Order create(OrderCreationRequest order){

        final OrderRoot orderRoot = new OrderRoot();
        order.getLines().forEach(l->orderRoot.addLine(l.getProductId(), l.getNb()));
        orderRoot.setOrderDate(new Date());
        orderRoot.setStatus("CREATED");
        orderRoot.setCustomerUsername(order.getCustomerUsername());
        orderRoot.setShopRef(order.getShopRef());

        OrderRoot root = orderRepository.save(orderRoot);
        return mapper.mapToDto(root);
    }

    public List<Order> findOrdersByCustomerUsername(String customerId){
        return this.orderRepository.findByCustomerUsername(customerId)
                .stream().map(mapper::mapToDto).toList();
    }

    public Optional<Order> findOrderById(Long orderId) {
        return this.orderRepository.findById(orderId).map(mapper::mapToDto);
    }

    public List<Order> findOrdersByShopRef(String shopRef) {
        return this.orderRepository.findByShopRef(shopRef)
                .stream().map(mapper::mapToDto).toList();
    }

    public Order updateOrderStatus(Long orderId, String newStatus) {
        this.orderRepository.findById(orderId).ifPresent(o->{
            o.setStatus(newStatus);
            this.orderRepository.save(o);
        });
        return this.findOrderById(orderId).get();
    }
}
