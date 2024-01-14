package com.example.tv2.api.models.request;

import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

public final class OrderRequests {


    @Validated
    public record ProductItemRequest(
            @NotNull String productId,
            @NotNull Integer quantity ,
            @NotNull BigDecimal price
            ) {
    }

    public record AddProduct(
            @NotNull String phoneNumber ,
            @NotNull List<ProductItemRequest> productItem
    ) {
    }




}
