package com.digitalcoffee.customer.mapper;

import com.digitalcoffee.customer.dto.Customer;
import com.digitalcoffee.customer.model.CustomerRoot;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface CustomerMapper {

    void updateCustomerRoot(@MappingTarget CustomerRoot customerRoot, Customer customer);

    Customer mapToDto(CustomerRoot root);
}
