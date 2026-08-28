package com.ecommerce.order_service.controller;


import com.ecommerce.order_service.dto.OrderRequestDTO;
import com.ecommerce.order_service.dto.OrderResponseDTO;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestHeader("X-User-id") Long customerId, @Valid @RequestBody OrderRequestDTO request) {

        OrderResponseDTO response = orderService.createOrder(customerId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderByID(@PathVariable UUID id) {

        OrderResponseDTO response = orderService.getOrderByID(id);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByCustomerID(@PathVariable Long customerId) {

        List<OrderResponseDTO> response = orderService.getOrderByCustomerID(customerId);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }


    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrderById(@RequestHeader("X-User-id") Long customerId,
                                                            @PathVariable UUID id,
                                                            @RequestHeader("X-User-Role") String role) {

        OrderResponseDTO response = orderService.cancelOrderById(id,customerId,role);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}


