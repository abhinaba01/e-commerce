package com.ecommerce.order_service.exception;



public class ProductDoesNotExistException extends RuntimeException {

    public ProductDoesNotExistException(String message){
        super(message);
    }
}
