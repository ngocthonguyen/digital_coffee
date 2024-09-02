package com.digitalcoffee.customer.dto;

import com.digitalcoffee.customer.proxy.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails {
    private Customer customer;

    private List<Order> orders;

}
