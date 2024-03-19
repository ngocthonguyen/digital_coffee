package com.digitalcoffee.order.repository;

import com.digitalcoffee.order.model.OrderRoot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderRoot, Long> {
    List<OrderRoot> findOrdersByCustomerUsername(String customerUdername);
}
