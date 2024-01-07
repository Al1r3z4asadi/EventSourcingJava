package com.example.tv2.utils;


import java.util.Optional;

public class ServiceResult<T> {
    private final boolean isSuccess;
    private final T value;
    private final String error;

    private ServiceResult(boolean isSuccess, T value, String error) {
        this.isSuccess = isSuccess;
        this.value = value;
        this.error = error;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(value);
    }

    public Optional<String> getError() {
        return Optional.ofNullable(error);
    }

    public static <T> ServiceResult<T> success(T value) {
        return new ServiceResult<>(true, value, null);
    }

    public static <T> ServiceResult<T> failure(String error) {
        return new ServiceResult<>(false, null, error);
    }
}