package com.ecommerce.events.order;

import java.util.List;

public record OrderCreatedEvent(

        Long orderId,
        Long userId,
        List<OrderItemEvent> items
) {
}
