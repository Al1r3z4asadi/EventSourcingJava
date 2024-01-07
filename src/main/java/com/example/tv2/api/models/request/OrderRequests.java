package com.example.tv2.api.models.request;

import org.jetbrains.annotations.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

public final class OrderRequests {


    @Validated
    public record ProductItemRequest(
            @NotNull String productId,
            @NotNull Integer quantity
    ) {
        public String getProductId() {
            return productId;
        }

        public Integer getQuantity() {
            return this.quantity ;
        }
    }

    public record AddProduct(
            @NotNull String phoneNumber ,
            @NotNull List<ProductItemRequest> productItem
    ) {
    }

}
