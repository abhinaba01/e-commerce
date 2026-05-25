package com.ecommerce.events.order;

public record OrderItemEvent(
        Long productId,
        Integer quantity
) {
}
