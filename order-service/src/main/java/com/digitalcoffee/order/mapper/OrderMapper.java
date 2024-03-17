package com.digitalcoffee.order.mapper;

import com.digitalcoffee.order.dto.Order;
import com.digitalcoffee.order.dto.OrderLine;
import com.digitalcoffee.order.model.OrderLineEntity;
import com.digitalcoffee.order.model.OrderRoot;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    Order mapToDto(OrderRoot root);

    OrderLine mapToDto(OrderLineEntity entity);

    OrderRoot mapToEntity(Order dto);

    OrderLineEntity mapToEntity(OrderLine dto);
}
