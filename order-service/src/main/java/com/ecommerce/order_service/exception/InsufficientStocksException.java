package com.ecommerce.order_service.exception;



public class InsufficientStocksException extends RuntimeException{

    public InsufficientStocksException(String message){
        super(message);
    }
}
