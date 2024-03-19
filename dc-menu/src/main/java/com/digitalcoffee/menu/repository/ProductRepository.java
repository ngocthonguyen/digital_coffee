package com.digitalcoffee.menu.repository;

import com.digitalcoffee.menu.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findByShopId(String shopId);
}
