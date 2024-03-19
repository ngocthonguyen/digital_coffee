package com.digitalcoffee.customer.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    private Long productId;
    private int nb;
}
