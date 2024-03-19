package com.digitalcoffee.menu.controller;

import com.digitalcoffee.menu.dto.Product;
import com.digitalcoffee.menu.service.ProductService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "")
    public List<Product> findProductByShopId(@RequestParam("shopId") @NonNull String shopId) {
        return productService.findProductsByShopId(shopId);
    }

    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable("productId") @NonNull Long productId) {
        return productService.findById(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/")
    public Product save(@RequestBody Product product){
        return productService.create(product);
    }
}
