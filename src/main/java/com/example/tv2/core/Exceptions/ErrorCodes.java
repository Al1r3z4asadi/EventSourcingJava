package com.example.tv2.core.Exceptions;

public enum ErrorCodes {
    INVALID_PRODUCT_ID("Invalid product ID", "ERR001"),
    PRODUCT_NOT_FOUND("Product not found", "ERR002"),
    CAN_NOT_APPLY_TO_EMPTY_EVENT("Event cannot be null! " , "ERR003"),
    UNSUPPORTED_EVENT("This event is not supported" , "ERR004"),
    INVALID_COUNT("COUNT_INVALID", "ERR00998"),

    GENERIC_ERROR("An unexpected error occurred", "ERR999");

    private final String message;
    private final String code;

    ErrorCodes(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
