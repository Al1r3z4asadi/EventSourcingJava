package com.example.tv2.core.Exceptions;

public class OrderException extends RuntimeException {

    private String errorCode;

    public OrderException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}