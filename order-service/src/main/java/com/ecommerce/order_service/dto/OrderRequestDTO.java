package com.ecommerce.order_service.dto;

import com.ecommerce.order_service.entity.OrderItems;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotEmpty
    private List<OrderItemRequestDTO> items ;
}
