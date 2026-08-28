package com.ecommerce.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {



    private String productId;

    private String productName;

    private String sellerId;

    private BigDecimal productPrice;

    private Integer quantity;

    private BigDecimal subtotal;

}
