package com.ecommerce.order_service.dto;

import com.ecommerce.order_service.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private UUID orderId;

    private Long customerId;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private List<OrderItemResponseDTO> items;
}