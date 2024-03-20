package com.digitalcoffee.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationRequest {

    private String customerUsername;
    private String shopRef;
    private List<OrderLine> lines = new ArrayList<>(0);

}
