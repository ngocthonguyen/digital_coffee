package com.magiccoffee.customer.mapper;

import com.magiccoffee.customer.dto.Customer;
import com.magiccoffee.customer.model.CustomerRoot;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface CustomerMapper {

    void updateCustomerRoot(@MappingTarget CustomerRoot customerRoot, Customer customer);

    Customer mapToDto(CustomerRoot root);
}
