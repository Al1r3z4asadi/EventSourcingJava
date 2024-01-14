package com.example.tv2.api.models;

import com.example.tv2.api.models.request.OrderRequests;
import com.example.tv2.core.Dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class MyMapper {


    public List<ProductDto> ToDto(List<OrderRequests.ProductItemRequest> productItemRequests) {
            return productItemRequests.stream()
                    .map(this::mapProductItemRequestToProductDto)
                    .collect(Collectors.toList());
    }
    private ProductDto mapProductItemRequestToProductDto(OrderRequests.ProductItemRequest productItemRequest) {
        return new ProductDto(
                productItemRequest.productId(),
                productItemRequest.quantity() ,
                productItemRequest.price()
        );
    }
}
