package com.digitalcoffee.menu.service;

import com.digitalcoffee.menu.dto.Product;
import com.digitalcoffee.menu.mapper.MenuMapper;
import com.digitalcoffee.menu.model.ProductEntity;
import com.digitalcoffee.menu.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MenuMapper mapper;

    public Product create(Product product){
        product.setId(null);
        ProductEntity productEntity = productRepository.save(mapper.mapToEntity(product));
        return mapper.mapToDto(productEntity);
    }

    public List<Product> findProductsByShopId(String customerId){
        return this.productRepository.findByShopId(customerId)
                .stream().map(mapper::mapToDto).toList();
    }

    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id).map(mapper::mapToDto);
    }

}
