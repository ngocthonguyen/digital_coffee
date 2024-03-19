package com.digitalcoffee.customer.proxy;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderList {
    List<Order> orders;
}
