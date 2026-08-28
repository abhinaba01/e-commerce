package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequestDTO;
import com.ecommerce.product_service.dto.ProductResponseDTO;
import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.exception.ProductDoesNotExistException;
import com.ecommerce.product_service.exception.UnauthorizedProductAccessException;
import com.ecommerce.product_service.mapper.ProductMapper;
import com.ecommerce.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper =  productMapper;

    }

    public ProductResponseDTO registerProduct(ProductRequestDTO request , String sellerId) {

        Product product = productMapper.toEntity(request);
        product.setSellerId(sellerId);

        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductById(String id) {

        return productRepository.findById(id).orElseThrow(() ->
                new ProductDoesNotExistException("Product Does Not Exist")
        );

    }

    public List<Product> getProductBySeller(String sellerId) {

        return productRepository.findBySellerId(sellerId);
    }


    public void deleteProduct(String productId , String sellerId){

        Product product = productRepository.findById(productId)
                            .orElseThrow(
                                    () -> new ProductDoesNotExistException("Product Does Not Exist"));

        if (!product.getSellerId().equals(sellerId)){
            throw new UnauthorizedProductAccessException("User does not own this product");
        }



         productRepository.deleteById(productId);
    }

    public Product updateProduct(String productId , String sellerId, Product newProduct){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductDoesNotExistException("Product Not Found"));

        if (!product.getSellerId().equals(sellerId)){
            throw new UnauthorizedProductAccessException("User does not own this product");
        }



        product.setDescription(newProduct.getDescription());
        product.setStockQuantity(newProduct.getStockQuantity());
        product.setPrice(newProduct.getPrice());
        product.setName(newProduct.getName());

        return productRepository.save(product);

    }

    public void reduceStock(String id , Integer quantity){

        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productRepository.findById(id)
                        .orElseThrow(() -> new ProductDoesNotExistException("Product Does Not Exist"));

        Integer stockQuantity = product.getStockQuantity();

        if (stockQuantity < quantity){
          throw new RuntimeException("Insufficient stocks ");
        }


        Integer newQuantity = stockQuantity - quantity;
        product.setStockQuantity(newQuantity);

        productRepository.save(product);

    }


    public void increaseStock(String id , Integer quantity){

        if(quantity < 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductDoesNotExistException("Product Does Not Exist"));

        Integer stockQuantity = product.getStockQuantity();

        Integer newQuantity = stockQuantity + quantity;
        product.setStockQuantity(newQuantity);

        productRepository.save(product);

    }

}
