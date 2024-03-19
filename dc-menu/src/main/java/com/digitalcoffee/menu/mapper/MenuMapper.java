package com.digitalcoffee.menu.mapper;

import com.digitalcoffee.menu.dto.Product;
import com.digitalcoffee.menu.model.ProductEntity;
import org.mapstruct.Mapper;

@Mapper
public interface MenuMapper {

    Product mapToDto(ProductEntity root);

    ProductEntity mapToEntity(Product dto);

}
