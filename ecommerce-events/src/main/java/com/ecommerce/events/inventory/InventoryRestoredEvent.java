package com.ecommerce.events.inventory;

import java.util.List;
import com.ecommerce.events.order.OrderItemEvent;

public record InventoryRestoredEvent(

        Long orderId,
        List<OrderItemEvent> items

) {
}