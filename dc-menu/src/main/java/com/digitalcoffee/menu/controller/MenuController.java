package com.digitalcoffee.menu.controller;

import com.digitalcoffee.menu.dto.Product;
import com.digitalcoffee.menu.service.ProductService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/menus")
public class MenuController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/search")
    public List<Product> findProductByShopId(@RequestParam("shopRef") @NonNull String shopRef) {
        return productService.findProductsByShopId(shopRef);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable("productId") @NonNull Long productId) {
        return productService.findById(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_SHOP_ADMIN')")
    public Product create(@RequestBody Product product){
        return productService.create(product);
    }
}
