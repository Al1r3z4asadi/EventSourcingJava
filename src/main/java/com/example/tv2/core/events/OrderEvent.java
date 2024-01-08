package com.example.tv2.core.events;

import com.example.tv2.core.models.Product;
import com.example.tv2.core.models.ProductItem;

import java.util.UUID;

public interface OrderEvent extends IEvent{

    record ProductAddedToOrder(
            UUID orderId ,
            int count ,
            ProductItem item
    ) implements OrderEvent {
    }


}
