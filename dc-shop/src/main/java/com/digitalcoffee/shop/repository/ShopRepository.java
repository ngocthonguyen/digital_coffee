package com.digitalcoffee.shop.repository;

import com.digitalcoffee.shop.model.ShopRoot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends CrudRepository<ShopRoot, Long> {
    Optional<ShopRoot> findByRef(String ref);
    Optional<ShopRoot> findByShopName(String name);
}
