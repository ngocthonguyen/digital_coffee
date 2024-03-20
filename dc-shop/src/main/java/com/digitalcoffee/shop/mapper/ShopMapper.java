package com.digitalcoffee.shop.mapper;

import com.digitalcoffee.shop.dto.Shop;
import com.digitalcoffee.shop.model.OperatorEntity;
import com.digitalcoffee.shop.model.ShopRoot;
import com.digitalcoffee.shop.repository.ShopRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ShopMapper {

    Shop mapToDto(ShopRoot root);

    default String map(OperatorEntity operatorEntity){
        return operatorEntity.getUsername();
    }

    //ShopRoot mapToEntity(Shop dto);

    @Mapping(target = "ref", ignore = true)
    @Mapping(target = "operators", ignore = true)
    void updateEntity(@MappingTarget ShopRoot entity, Shop dto);
}
