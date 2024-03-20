package com.digitalcoffee.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    private String shopRef;
    private String shopName;
    private String ownerUsername;
    private String phoneNumber;
    private String address;
    private List<String> operators = new ArrayList<>(0);

}
