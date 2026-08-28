package com.ecommerce.order_service.mapper;

import com.ecommerce.order_service.dto.OrderRequestDTO;
import com.ecommerce.order_service.dto.OrderResponseDTO;
import com.ecommerce.order_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "uuid", ignore = true)

    Order toEntity(OrderRequestDTO dto);


    @Mapping(target = "orderId", source = "uuid")
    OrderResponseDTO toDTO(Order order);
}