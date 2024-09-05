package com.iotiq.application.exception.orderexceptions;

public class OrderNotFoundExcption extends RuntimeException {
    public OrderNotFoundExcption(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
