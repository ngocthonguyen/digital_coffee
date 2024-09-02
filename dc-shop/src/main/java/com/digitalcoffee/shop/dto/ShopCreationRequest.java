package com.digitalcoffee.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopCreationRequest {

    private String ref;
    private String shopName;
    private String ownerUsername;

}
