package com.ecommerce.events.order;

import java.util.List;

public record OrderCancelledEvent(

        Long orderId,
        Long userId,
        List<OrderItemEvent> items

) {
}