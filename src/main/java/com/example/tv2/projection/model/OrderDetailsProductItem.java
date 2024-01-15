package com.example.tv2.projection.model;


import com.example.tv2.core.models.ProductItem;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class OrderDetailsProductItem {
    @Id
    private UUID productId;

    public OrderDetailsProductItem(List<ProductItem> items){

    }

    public OrderDetailsProductItem() {

    }

    public UUID getProductId() {
        return  productId ;
    }
}
