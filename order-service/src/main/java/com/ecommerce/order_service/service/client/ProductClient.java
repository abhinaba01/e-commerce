package com.ecommerce.order_service.service.client;

import com.ecommerce.order_service.dto.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "product-service",
        url = "${product.service.url}"
)
public interface ProductClient {


    @GetMapping("/product/{id}")
    ProductResponseDTO getProductById(@PathVariable("id") String id);


    @PutMapping("/internal/{id}/reduce-stock")
    void reduceStock(
            @PathVariable("id") String id,
            @RequestParam("quantity") Integer quantity
    );

    @PutMapping("/internal/{id}/increase-stock")
    void increaseStock(
            @PathVariable("id") String id,
            @RequestParam("quantity") Integer quantity
    );
}