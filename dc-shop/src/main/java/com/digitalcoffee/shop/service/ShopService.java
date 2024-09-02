package com.digitalcoffee.shop.service;

import com.digitalcoffee.shop.dto.Shop;
import com.digitalcoffee.shop.mapper.ShopMapper;
import com.digitalcoffee.shop.model.OperatorEntity;
import com.digitalcoffee.shop.model.ShopRoot;
import com.digitalcoffee.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ShopMapper mapper;

    public Shop create(String ref, String shopName, String shopOwner){
        ShopRoot root = ShopRoot.builder()
                .ref(ref)
                .shopName(shopName)
                .ownerUsername(shopOwner).build();
        root.addOperator(shopOwner);
        root = shopRepository.save(root);
        return mapper.mapToDto(root);
    }

    public Optional<Shop> findShopByName(String ref){
        return this.shopRepository.findByShopName(ref)
                .map(mapper::mapToDto);
    }

    public Optional<Shop> findShopByRef(String ref) {
        return this.shopRepository.findByRef(ref).map(mapper::mapToDto);
    }

    public Shop updateShop(Shop shop) {
        ShopRoot root = shopRepository.findByRef(shop.getRef()).get();

        mapper.updateEntity(root, shop);
        root.getOperators().clear();
        root.addOperator(shop.getOwnerUsername());
        shop.getOperators().forEach(root::addOperator);

        root = shopRepository.save(root);
        return mapper.mapToDto(root);
    }
}
