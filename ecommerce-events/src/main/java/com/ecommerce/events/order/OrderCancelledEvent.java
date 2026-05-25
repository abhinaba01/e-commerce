package com.ecommerce.events.order;

public record OrderCancelledEvent(
        Long customerId
) {
}
