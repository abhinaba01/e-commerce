package com.ecommerce.product_service.exception;


public class ProductDoesNotExistException extends RuntimeException{

    public ProductDoesNotExistException(String message){
        super(message);

    }
}
