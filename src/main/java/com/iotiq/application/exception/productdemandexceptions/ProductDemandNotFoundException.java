package com.iotiq.application.exception.productdemandexceptions;


public class ProductDemandNotFoundException extends RuntimeException{
    public ProductDemandNotFoundException(String message) {
        super(message);
    }

    public ProductDemandNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
