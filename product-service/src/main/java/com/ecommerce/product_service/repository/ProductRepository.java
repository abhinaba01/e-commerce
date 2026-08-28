package com.ecommerce.product_service.repository;

import com.ecommerce.product_service.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {


    @Query("{ 'sellerId': ?0 }")
    List<Product> findBySellerId(String sellerId);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<Product> searchProducts(String keyword);

}

