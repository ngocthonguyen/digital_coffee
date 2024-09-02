package com.digitalcoffee.shop.controller;

import com.digitalcoffee.shop.dto.ShopCreationRequest;
import com.digitalcoffee.shop.dto.Shop;
import com.digitalcoffee.shop.mapper.ShopMapper;
import com.digitalcoffee.shop.service.ShopService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping(value = "/search")
    public ResponseEntity<Shop> findByShopName(@RequestParam("shopName") @NonNull String shopName) {
        return shopService.findShopByName(shopName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{ref}")
    public ResponseEntity<Shop> findByRef(@PathVariable("ref") @NonNull String ref) {
        return shopService.findShopByRef(ref)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Object> create(@RequestBody ShopCreationRequest request){
        if (shopService.findShopByRef(request.getRef()).isPresent()){
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Shop already exists with ref " + request.getRef());
        }
        return ResponseEntity.ok(shopService.create(request.getRef(),
                request.getShopName(),
                request.getOwnerUsername()));
    }

    @PutMapping(value = "/{ref}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_SHOP_ADMIN')")
    public ResponseEntity<Shop> update(@PathVariable("ref") @NonNull String ref,
                                       @RequestBody Shop shop){
        if (shopService.findShopByRef(ref).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shopService.updateShop(shop));
    }
}
