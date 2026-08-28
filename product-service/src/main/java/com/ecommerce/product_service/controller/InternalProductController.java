package com.ecommerce.product_service.controller;


import com.ecommerce.product_service.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal")
public class InternalProductController {

    private final ProductService productService;


    public InternalProductController(ProductService productService) {
        this.productService = productService;
    }

    @PutMapping("/{id}/reduce-stock")
    public void reduceStock (@PathVariable String id, @RequestParam Integer quantity){
        productService.reduceStock(id,quantity);
    }

    @PutMapping("/{id}/increase-stock")
    public void increaseStock (@PathVariable String id, @RequestParam Integer quantity){
        productService.increaseStock(id,quantity);
    }
}
