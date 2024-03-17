package com.magiccoffee.customer.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private Long id;
    private Date orderDate;
    private Long customerId;
    private Long shopId;
    private String status;
    private List<OrderLine> products = new ArrayList<>(0);

}
